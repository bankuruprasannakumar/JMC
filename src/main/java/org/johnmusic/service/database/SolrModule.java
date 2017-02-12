package org.johnmusic.service.database;

import com.google.inject.AbstractModule;
import org.johnmusic.service.database.adapter.IDataAccessAdapter;
import org.johnmusic.service.database.adapter.solr.SolrAdapter;
import org.johnmusic.service.database.dao.*;
import org.johnmusic.service.pojo.BatchAttendance;

import static jdk.nashorn.internal.objects.NativeFunction.bind;

/**
 * Created by bankuru on 11/2/17.
 */

public class SolrModule  extends AbstractModule {
    @Override
    public void configure(){
        bind(IDataAccessAdapter.class).to(SolrAdapter.class);
        bind(IBatchDetailsDao.class).to(BatchDetailsDao.class);
        bind(IStudentDetailsDao.class).to(StudentDetailsDao.class);
        bind(IStudentBatchAssociationDetailsDao.class).to(StudentBatchAssociationDetailsDao.class);
        bind(IBatchAttendanceDao.class).to(BatchAttendanceDao.class);
    }

}
