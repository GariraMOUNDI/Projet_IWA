package org.projet_iwa.location.api.service;

import org.projet_iwa.location.api.model.ClusterDTO;
import org.projet_iwa.location.api.model.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DetectionThread implements Runnable {

    @Autowired
    private LocationService locationService;

    private List<LocationDTO> locations;
    private final int locationsCount;
    private final Timestamp middleOfInterval;
    private static final double warningDistance = 2 ; // in meters

    DetectionThread(List<LocationDTO> locations, long maximumInterval) {
        long latestDate = locations.get(locations.size()-1).getDate().getTime();

        this.locations = locations;
        this.locationsCount = locations.size();
        this.middleOfInterval = new Timestamp(latestDate - (maximumInterval/2));
    }

    public void run() {

        // This algorithm calculates distances between each locations.
        // To do so without redundancy, it uses a fictional triangular matrix
        // and iterates on it following this concept (abscissa, ordinate).
        //
        // Why is the matrix triangular ?
        // The diagonal is null since distance between Point A and Point A is null.
        // Distances are symmetrical because A to B = B to A so we don't need to compute twice the operation
        //
        // Then only relevant locations (distances under warning distance) are selected (preCluster)
        // Then several complementary test are made in order to avoid redundancy (hasNewLocation, isNewCluster)
        // Finally, if the preCluster has succeeded the tests, the algorithm creates a real cluster which means making
        // one step toward anonymous user-related-data (GDPR compliant) and producing average cluster-related-data
        //
        // Once it has iterates over all locations, produce cluster events based on cluster array and send them to Kafka


        // Matrix variables
        int abscissa = 1; // Because (0,0) would be null
        int ordinate = 0;
        double currentDistance = 0;
        LocationDTO abscissaLocation;
        LocationDTO ordinateLocation;

        // Cluster variables
        List<LocationDTO> preCluster= new ArrayList<>(); // Raw locations without processed data
        List<ClusterDTO> clusters = new ArrayList<>(); // Average data compute only after succeeding tests
            // Time test
        boolean hasNewLocation = false;
            // Redundancy Test
        int clusterCounter = 0;
        boolean isNewCluster = false;
            // Variables used during processing of the cluster
        List<String> preUserList = new ArrayList<>();
        List<Long> preLatitudeList = new ArrayList<>();
        List<Long> preLongitudeList = new ArrayList<>();
        String[] userList;
        Timestamp approximateDate;


        // Iterating over the conceptual matrix
        while (ordinate < locationsCount - 1) {
            ordinateLocation = locations.get(ordinate);
            preCluster.add(ordinateLocation);


            while (abscissa < locationsCount) {
                abscissaLocation = locations.get(abscissa);

//                currentDistance = distance(
//                        abscissaLocation.getLatitude(),
//                        ordinateLocation.getLatitude(),
//                        abscissaLocation.getLongitude(),
//                        ordinateLocation.getLongitude(),
//                        0.0,
//                        0.0);

                // Selection for the cluster before testing
                if (currentDistance < warningDistance) {
                    preCluster.add(abscissaLocation);
                }

                abscissa += 1;
            }

            // Testing the selection once the raw iteration is complete
            if (preCluster.size() > 1) {

                // Test 1 : Since threads are overlapping, the potential cluster must add some new information
                hasNewLocation = preCluster.get(locationsCount-1).getDate().after(middleOfInterval);

                // Test 2 : Redundancy must be avoided to increase performance of further treatment
                //          Thanks to the triangular matrix, same clusters cannot be computed twice
                //          However, sample of previous cluster cannot be avoided so they must be identified and ignored
                if (hasNewLocation) {

                    // Retrieving potential user list
                    for (LocationDTO location : locations) {
//                        preUserList.add(location.getUser_id());
//                        preUserList.add(location.getUser_id().toString());

                    }
                    userList = preUserList.toArray(new String[preUserList.size()]);

                    // Researching for redundancy with existing clusters
                    isNewCluster = true;
                    clusterCounter = 0;
                    while (isNewCluster && clusterCounter < clusters.size()) {
                        isNewCluster = !clusters.get(clusterCounter).containsEach(userList);
                        clusterCounter += 1;
                    }

                    // Creating the new cluster and adding it to the cluster list
                    if (isNewCluster) {
                        approximateDate = preCluster.get(preCluster.size()/2).getDate();
                        for (LocationDTO location : locations) {
                            preLatitudeList.add(location.getLatitude());
                            preLongitudeList.add(location.getLongitude());
                        }
                        clusters.add(new ClusterDTO(average(preLatitudeList), average(preLongitudeList), approximateDate, userList));
                    }

                }

            }

            ordinate += 1;
            abscissa = 1 + ordinate; // Because the matrix is triangular
            preCluster.clear();
            preUserList.clear();
            preLatitudeList.clear();
            preLongitudeList.clear();
        }


        // Producing event for each cluster and sending them to Kafka
        for (ClusterDTO cluster : clusters) {
            locationService.sendCluster(cluster);
        }

    }



    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @return Distance in Meters
     * @author David George
     */
//    private static double distance(double lat1, double lat2, double lon1,
//                                  double lon2, double el1, double el2) {
//
//        final int R = 6371; // Radius of the earth
//
//        double latDistance = Math.toRadians(lat2 - lat1);
//        double lonDistance = Math.toRadians(lon2 - lon1);
//        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
//                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
//                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double distance = R * c * 1000; // convert to meters
//
//        double height = el1 - el2;
//
//        distance = Math.pow(distance, 2) + Math.pow(height, 2);
//
//        return Math.sqrt(distance);
//    }



    private static long average(List<Long> list) {
        long sum = 0;
        for (long i : list) {
            sum += i;
        }
        return sum / list.size();
    }
}
