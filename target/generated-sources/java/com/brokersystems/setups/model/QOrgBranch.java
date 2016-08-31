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

    public final QAuditBaseEntity _super = new QAuditBaseEntity(this);

    public final StringPath address = createString("address");

    public final QUser branchManager;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> obId = createNumber("obId", Long.class);

    public final StringPath obName = createString("obName");

    public final StringPath obShtDesc = createString("obShtDesc");

    public final QOrgRegions region;

    public final ListPath<RentalStructure, QRentalStructure> rentalStructures = this.<RentalStructure, QRentalStructure>createList("rentalStructures", RentalStructure.class, QRentalStructure.class, PathInits.DIRECT2);

    public final StringPath telNumber = createString("telNumber");

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
        this.branchManager = inits.isInitialized("branchManager") ? new QUser(forProperty("branchManager")) : null;
        this.region = inits.isInitialized("region") ? new QOrgRegions(forProperty("region"), inits.get("region")) : null;
    }

}

