package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1994355746L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final StringPath proClaimPrefix = createString("proClaimPrefix");

    public final NumberPath<Long> proCode = createNumber("proCode", Long.class);

    public final StringPath proDesc = createString("proDesc");

    public final QProductGroup productGroup;

    public final NumberPath<java.math.BigDecimal> proMinPrem = createNumber("proMinPrem", java.math.BigDecimal.class);

    public final StringPath proPolicyPrefix = createString("proPolicyPrefix");

    public final StringPath proRenewable = createString("proRenewable");

    public final StringPath proSameDayRenewal = createString("proSameDayRenewal");

    public final StringPath proShtDesc = createString("proShtDesc");

    public final DatePath<java.util.Date> proWef = createDate("proWef", java.util.Date.class);

    public final DatePath<java.util.Date> proWet = createDate("proWet", java.util.Date.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduct(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProduct(PathMetadata<?> metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productGroup = inits.isInitialized("productGroup") ? new QProductGroup(forProperty("productGroup")) : null;
    }

}

