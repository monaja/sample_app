package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCountry is a Querydsl query type for Country
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCountry extends EntityPathBase<Country> {

    private static final long serialVersionUID = -1033306423L;

    public static final QCountry country = new QCountry("country");

    public final NumberPath<Long> couCode = createNumber("couCode", Long.class);

    public final StringPath couName = createString("couName");

    public final ListPath<County, QCounty> counties = this.<County, QCounty>createList("counties", County.class, QCounty.class, PathInits.DIRECT2);

    public final StringPath couShtDesc = createString("couShtDesc");

    public QCountry(String variable) {
        super(Country.class, forVariable(variable));
    }

    public QCountry(Path<? extends Country> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountry(PathMetadata<?> metadata) {
        super(Country.class, metadata);
    }

}

