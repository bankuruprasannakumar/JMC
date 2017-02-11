package org.johnmusic.service.database;

import com.google.inject.AbstractModule;
import org.johnmusic.service.database.adapter.IDataAccessAdapter;
import org.johnmusic.service.database.adapter.solr.SolrAdapter;

import static jdk.nashorn.internal.objects.NativeFunction.bind;

/**
 * Created by bankuru on 11/2/17.
 */

public class SolrModule  extends AbstractModule {
    @Override
    public void configure(){
        bind(IDataAccessAdapter.class).to(SolrAdapter.class);
    }

}
