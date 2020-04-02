package com.github.poad.openjdk.finder.backend.repository;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface JavaVersionRepository {
    @SqlUpdate("INSERT INTO java_version(id, vendor, major_version, arch, version, url, type, impl, os, timestamp) VALUES (:id, :vendor, :majorVersion, :arch, :version, :url, :type, :impl, :os, :timestamp)")
    void insert(
            @Bind("id") String id,
            @Bind("vendor") String vendor,
            @Bind("majorVersion") int majorVersion,
            @Bind("arch") String arch,
            @Bind("version") String version,
            @Bind("url") String url,
            @Bind("type") String type,
            @Bind("impl") String impl,
            @Bind("os") String os,
            @Bind("timestamp") String timestamp);

    @SqlUpdate("UPDATE java_version SET vendor = :vendor, major_version = :majorVersion, arch = :arch, version = :version, url = :url, type = :type, impl = :impl, os = :os, timestamp = :timestamp WHERE id = :id")
    void update(
            @Bind("id") String id,
            @Bind("vendor") String vendor,
            @Bind("majorVersion") int majorVersion,
            @Bind("arch") String arch,
            @Bind("version") String version,
            @Bind("url") String url,
            @Bind("type") String type,
            @Bind("impl") String impl,
            @Bind("os") String os,
            @Bind("timestamp") String timestamp);

    @SqlQuery("SELECT count(*) FROM java_version WHERE id = :id")
    long countById(@Bind("id") String id);
}
