package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBank is a Querydsl query type for Bank
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBank extends EntityPathBase<Bank> {

    private static final long serialVersionUID = -289283095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBank bank = new QBank("bank");

    public final QAuditBaseEntity _super = new QAuditBaseEntity(this);

    public final NumberPath<Long> bankCode = createNumber("bankCode", Long.class);

    public final StringPath bankName = createString("bankName");

    public final StringPath bankShtDesc = createString("bankShtDesc");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDate = _super.modifiedDate;

    public final QOrganization organization;

    public QBank(String variable) {
        this(Bank.class, forVariable(variable), INITS);
    }

    public QBank(Path<? extends Bank> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBank(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBank(PathMetadata<?> metadata, PathInits inits) {
        this(Bank.class, metadata, inits);
    }

    public QBank(Class<? extends Bank> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.organization = inits.isInitialized("organization") ? new QOrganization(forProperty("organization"), inits.get("organization")) : null;
    }

}

