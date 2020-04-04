package com.github.poad.openjdk.finder.repository;

import com.github.poad.openjdk.finder.entity.JavaVersion;
import org.springframework.data.jpa.domain.Specification;

public class JavaVersionSpecs {
    private JavaVersionSpecs() {}

    public static Specification<JavaVersion> majorVersion(Integer majorVersion) {
        return majorVersion == null ? null : (root, query, builder) ->
                builder.equal(root.get("majorVersion"), majorVersion);
    }

    public static Specification<JavaVersion> vendor(String vendor) {
        return vendor == null ? null : (root, query, builder) ->
                builder.equal(root.get("vendor"), vendor);
    }

    public static Specification<JavaVersion> distribution(String distribution) {
        return distribution == null ? null : (root, query, builder) ->
                builder.equal(root.get("distribution"), distribution);
    }

    public static Specification<JavaVersion> arch(String arch) {
        return arch == null ? null : (root, query, builder) ->
                builder.equal(root.get("arch"), arch);
    }

    public static Specification<JavaVersion> installationType(String installationType) {
        return installationType == null ? null : (root, query, builder) ->
                builder.equal(root.get("installationType"), installationType);
    }

    public static Specification<JavaVersion> type(String type) {
        return type == null ? null : (root, query, builder) ->
                builder.equal(root.get("type"), type);
    }

    public static Specification<JavaVersion> bundle(String bundle) {
        return bundle == null ? null : (root, query, builder) ->
                builder.equal(root.get("bundle"), bundle);
    }

    public static Specification<JavaVersion> fx(Boolean fx) {
        return fx == null ? null : (root, query, builder) ->
                builder.equal(root.get("fx"), fx);
    }

    public static Specification<JavaVersion> os(String os) {
        return os == null ? null : (root, query, builder) ->
                builder.equal(root.get("os"), os);
    }
}
