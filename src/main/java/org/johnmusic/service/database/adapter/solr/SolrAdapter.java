package org.johnmusic.service.database.adapter.solr;

import org.johnmusic.service.Constants;
import org.johnmusic.service.database.adapter.IDataAccessAdapter;
import org.johnmusic.service.network.NetworkConnection;
import org.johnmusic.service.response.ResponseData;

/**
 * Created by bankuru on 11/2/17.
 */
public class SolrAdapter implements IDataAccessAdapter<ResponseData>{
    NetworkConnection connection;

    public SolrAdapter() {
        connection = new NetworkConnection();
    }

    @Override
    public ResponseData updateRequest(String query) {
        return connection.makeConnection(Constants.DB_UPDATE_ENDPOINT, NetworkConnection.METHOD.POST, query);
    }

    @Override
    public ResponseData selectRequest(String query) {
        return connection.makeConnection(Constants.DB_SELECT_ENDPOINT, NetworkConnection.METHOD.GET, query);
    }
}
