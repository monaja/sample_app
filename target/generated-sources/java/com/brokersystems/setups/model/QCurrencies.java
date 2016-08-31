package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCurrencies is a Querydsl query type for Currencies
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCurrencies extends EntityPathBase<Currencies> {

    private static final long serialVersionUID = -1272960932L;

    public static final QCurrencies currencies = new QCurrencies("currencies");

    public final QAuditBaseEntity _super = new QAuditBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> curCode = createNumber("curCode", Long.class);

    public final StringPath curIsoCode = createString("curIsoCode");

    public final StringPath curName = createString("curName");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath fraction = createString("fraction");

    public final NumberPath<Integer> fractionUnits = createNumber("fractionUnits", Integer.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> modifiedDate = _super.modifiedDate;

    public final ListPath<Organization, QOrganization> organizations = this.<Organization, QOrganization>createList("organizations", Organization.class, QOrganization.class, PathInits.DIRECT2);

    public QCurrencies(String variable) {
        super(Currencies.class, forVariable(variable));
    }

    public QCurrencies(Path<? extends Currencies> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCurrencies(PathMetadata<?> metadata) {
        super(Currencies.class, metadata);
    }

}

