package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrgBranch is a Querydsl query type for OrgBranch
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrgBranch extends EntityPathBase<OrgBranch> {

    private static final long serialVersionUID = -953373671L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrgBranch orgBranch = new QOrgBranch("orgBranch");

    public final NumberPath<Long> obId = createNumber("obId", Long.class);

    public final StringPath obName = createString("obName");

    public final StringPath obShtDesc = createString("obShtDesc");

    public final QOrganization organization;

    public QOrgBranch(String variable) {
        this(OrgBranch.class, forVariable(variable), INITS);
    }

    public QOrgBranch(Path<? extends OrgBranch> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrgBranch(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrgBranch(PathMetadata<?> metadata, PathInits inits) {
        this(OrgBranch.class, metadata, inits);
    }

    public QOrgBranch(Class<? extends OrgBranch> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.organization = inits.isInitialized("organization") ? new QOrganization(forProperty("organization"), inits.get("organization")) : null;
    }

}

