package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSysLocale is a Querydsl query type for SysLocale
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSysLocale extends EntityPathBase<SysLocale> {

    private static final long serialVersionUID = 2065191418L;

    public static final QSysLocale sysLocale = new QSysLocale("sysLocale");

    public final StringPath locDesc = createString("locDesc");

    public final NumberPath<Long> locId = createNumber("locId", Long.class);

    public final StringPath locName = createString("locName");

    public final ListPath<Organization, QOrganization> organizations = this.<Organization, QOrganization>createList("organizations", Organization.class, QOrganization.class, PathInits.DIRECT2);

    public QSysLocale(String variable) {
        super(SysLocale.class, forVariable(variable));
    }

    public QSysLocale(Path<? extends SysLocale> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysLocale(PathMetadata<?> metadata) {
        super(SysLocale.class, metadata);
    }

}

