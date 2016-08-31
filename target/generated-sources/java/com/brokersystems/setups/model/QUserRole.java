package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUserRole is a Querydsl query type for UserRole
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserRole extends EntityPathBase<UserRole> {

    private static final long serialVersionUID = -1862254066L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRole userRole = new QUserRole("userRole");

    public final QAuditBaseEntity _super = new QAuditBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDate = _super.modifiedDate;

    public final StringPath role = createString("role");

    public final QUser user;

    public final NumberPath<Long> userroleid = createNumber("userroleid", Long.class);

    public QUserRole(String variable) {
        this(UserRole.class, forVariable(variable), INITS);
    }

    public QUserRole(Path<? extends UserRole> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRole(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRole(PathMetadata<?> metadata, PathInits inits) {
        this(UserRole.class, metadata, inits);
    }

    public QUserRole(Class<? extends UserRole> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

