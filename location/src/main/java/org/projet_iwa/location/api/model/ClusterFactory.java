package org.projet_iwa.location.api.model;

import org.springframework.stereotype.Component;

@Component
public class ClusterFactory {
    public Cluster createClusterModel(ClusterDTO clusterDTO){
        return new Cluster(
                clusterDTO.getAverageLatitude(),
                clusterDTO.getAverageLongitude(),
                clusterDTO.getApproximateDate(),
                clusterDTO.getIdUsers()
        );
    }
}
