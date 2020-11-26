package org.projet_iwa.location.api.service;

import org.projet_iwa.location.api.model.LocationDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class DetectionRunnerThread extends TimerTask {

    private List<LocationDTO> recentLocations;
    private long maximumInterval;

    DetectionRunnerThread(List<LocationDTO> recentLocations, long maximumInterval) {
        this.recentLocations = recentLocations;
        this.maximumInterval = maximumInterval;
    }

    public void run() {

        // Deleting locations older than the maximum interval

        Timestamp latestDate = recentLocations.get(recentLocations.size()-1).getDate();
        Timestamp maximumDate = new Timestamp(latestDate.getTime()-maximumInterval);

        boolean isAnOldLocation = true;
        while (isAnOldLocation && recentLocations.size() > 1) {
            if (recentLocations.get(0).getDate().after(maximumDate)) {
                isAnOldLocation = false;
            }
            else {
                recentLocations.remove(0);
            }
        }

        // Copying the array and creating the detection thread

        if (recentLocations.size() > 1) {

            List<LocationDTO> locationToDetect = new ArrayList<>(recentLocations);

            DetectionThread detection = new DetectionThread(locationToDetect, maximumInterval);
            Thread detectionThread = new Thread(detection);
            detectionThread.start();
        }

    }

}
