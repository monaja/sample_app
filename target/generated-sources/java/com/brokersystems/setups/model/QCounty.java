package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCounty is a Querydsl query type for County
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCounty extends EntityPathBase<County> {

    private static final long serialVersionUID = 1213593527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCounty county = new QCounty("county");

    public final QCountry country;

    public final StringPath countyCode = createString("countyCode");

    public final NumberPath<Long> countyId = createNumber("countyId", Long.class);

    public final StringPath countyName = createString("countyName");

    public final ListPath<Town, QTown> towns = this.<Town, QTown>createList("towns", Town.class, QTown.class, PathInits.DIRECT2);

    public QCounty(String variable) {
        this(County.class, forVariable(variable), INITS);
    }

    public QCounty(Path<? extends County> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCounty(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCounty(PathMetadata<?> metadata, PathInits inits) {
        this(County.class, metadata, inits);
    }

    public QCounty(Class<? extends County> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
    }

}

