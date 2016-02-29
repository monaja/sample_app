package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBrokerEntity is a Querydsl query type for BrokerEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBrokerEntity extends EntityPathBase<BrokerEntity> {

    private static final long serialVersionUID = 475079113L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBrokerEntity brokerEntity = new QBrokerEntity("brokerEntity");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> dob = createDateTime("dob", java.util.Date.class);

    public final StringPath emailAddress = createString("emailAddress");

    public final NumberPath<Long> entityCode = createNumber("entityCode", Long.class);

    public final EnumPath<EntityType> entityType = createEnum("entityType", EntityType.class);

    public final StringPath idno = createString("idno");

    public final StringPath mobileNumber = createString("mobileNumber");

    public final StringPath names = createString("names");

    public final StringPath othernames = createString("othernames");

    public final StringPath physicalAddress = createString("physicalAddress");

    public final StringPath pin = createString("pin");

    public final QAddress postalAddress;

    public final StringPath shtDesc = createString("shtDesc");

    public final EnumPath<EntityStatus> status = createEnum("status", EntityStatus.class);

    public final StringPath telNumber = createString("telNumber");

    public final DateTimePath<java.util.Date> wef = createDateTime("wef", java.util.Date.class);

    public QBrokerEntity(String variable) {
        this(BrokerEntity.class, forVariable(variable), INITS);
    }

    public QBrokerEntity(Path<? extends BrokerEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBrokerEntity(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBrokerEntity(PathMetadata<?> metadata, PathInits inits) {
        this(BrokerEntity.class, metadata, inits);
    }

    public QBrokerEntity(Class<? extends BrokerEntity> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.postalAddress = inits.isInitialized("postalAddress") ? new QAddress(forProperty("postalAddress"), inits.get("postalAddress")) : null;
    }

}

