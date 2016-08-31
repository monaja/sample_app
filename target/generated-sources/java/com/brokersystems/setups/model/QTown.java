package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTown is a Querydsl query type for Town
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTown extends EntityPathBase<Town> {

    private static final long serialVersionUID = -288733121L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTown town = new QTown("town");

    public final QAuditBaseEntity _super = new QAuditBaseEntity(this);

    public final QCounty county;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> ctCode = createNumber("ctCode", Long.class);

    public final StringPath ctName = createString("ctName");

    public final StringPath ctPostalCode = createString("ctPostalCode");

    public final StringPath ctShtDesc = createString("ctShtDesc");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDate = _super.modifiedDate;

    public QTown(String variable) {
        this(Town.class, forVariable(variable), INITS);
    }

    public QTown(Path<? extends Town> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTown(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTown(PathMetadata<?> metadata, PathInits inits) {
        this(Town.class, metadata, inits);
    }

    public QTown(Class<? extends Town> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.county = inits.isInitialized("county") ? new QCounty(forProperty("county"), inits.get("county")) : null;
    }

}

