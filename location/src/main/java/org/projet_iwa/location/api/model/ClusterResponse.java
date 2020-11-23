package org.projet_iwa.location.api.model;

public class ClusterResponse extends Response<ClusterResponseType, Cluster> {

    public ClusterResponse(ClusterResponseType clusterResponseType){
        super(clusterResponseType, null);
    }
}
