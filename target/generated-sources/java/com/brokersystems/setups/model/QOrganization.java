package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrganization is a Querydsl query type for Organization
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrganization extends EntityPathBase<Organization> {

    private static final long serialVersionUID = 938135008L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrganization organization = new QOrganization("organization");

    public final QAddress address;

    public final ListPath<Bank, QBank> banks = this.<Bank, QBank>createList("banks", Bank.class, QBank.class, PathInits.DIRECT2);

    public final QCurrencies currency;

    public final NumberPath<Long> orgCode = createNumber("orgCode", Long.class);

    public final StringPath orgDesc = createString("orgDesc");

    public final StringPath orgFax = createString("orgFax");

    public final ArrayPath<byte[], Byte> orgLogo = createArray("orgLogo", byte[].class);

    public final StringPath orgMobile = createString("orgMobile");

    public final StringPath orgName = createString("orgName");

    public final StringPath orgPhone = createString("orgPhone");

    public final ListPath<OrgRegions, QOrgRegions> orgRegions = this.<OrgRegions, QOrgRegions>createList("orgRegions", OrgRegions.class, QOrgRegions.class, PathInits.DIRECT2);

    public final StringPath orgShtDesc = createString("orgShtDesc");

    public final StringPath orgWebsite = createString("orgWebsite");

    public final QSysLocale sysLocale;

    public QOrganization(String variable) {
        this(Organization.class, forVariable(variable), INITS);
    }

    public QOrganization(Path<? extends Organization> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganization(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganization(PathMetadata<?> metadata, PathInits inits) {
        this(Organization.class, metadata, inits);
    }

    public QOrganization(Class<? extends Organization> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address"), inits.get("address")) : null;
        this.currency = inits.isInitialized("currency") ? new QCurrencies(forProperty("currency")) : null;
        this.sysLocale = inits.isInitialized("sysLocale") ? new QSysLocale(forProperty("sysLocale")) : null;
    }

}

