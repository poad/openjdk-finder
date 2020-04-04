package com.github.poad.openjdk.finder.backend.repository;

import com.github.poad.openjdk.finder.backend.entity.JavaVersion;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface JavaVersionRepository {
    @SqlUpdate("INSERT INTO java_version(id, vendor, distribution, major_version, arch, version, installation_type, extension, url, type, bundle, os, fx, timestamp) VALUES (:id, :vendor, :distribution, :majorVersion, :arch, :version, :installationType, :extension, :url, :type, :bundle, :os, :fx, :timestamp)")
    void insert(@BindBean JavaVersion version);

    @SqlUpdate("UPDATE java_version SET vendor = :vendor, distribution = :distribution, major_version = :majorVersion, arch = :arch, version = :version, installation_type = :installationType, extension = :extension, url = :url, type = :type, bundle = :bundle, os = :os, fx = :fx, timestamp = :timestamp WHERE id = :id")
    void update(@BindBean JavaVersion version);

    @SqlQuery("SELECT count(id) FROM java_version WHERE id = :id")
    long countById(@Bind("id") String id);

    @SqlUpdate("DELETE from java_version WHERE distribution = :distribution and id not in(<ids>)")
    void delete(@Bind("distribution") String distribution,@BindList("ids") List<String> ids);


}
