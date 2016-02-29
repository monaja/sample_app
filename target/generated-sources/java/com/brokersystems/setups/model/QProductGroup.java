package com.brokersystems.setups.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductGroup is a Querydsl query type for ProductGroup
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductGroup extends EntityPathBase<ProductGroup> {

    private static final long serialVersionUID = 751556477L;

    public static final QProductGroup productGroup = new QProductGroup("productGroup");

    public final NumberPath<Long> prgCode = createNumber("prgCode", Long.class);

    public final StringPath prgDescn = createString("prgDescn");

    public final StringPath prgType = createString("prgType");

    public final ListPath<Product, QProduct> products = this.<Product, QProduct>createList("products", Product.class, QProduct.class, PathInits.DIRECT2);

    public QProductGroup(String variable) {
        super(ProductGroup.class, forVariable(variable));
    }

    public QProductGroup(Path<? extends ProductGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductGroup(PathMetadata<?> metadata) {
        super(ProductGroup.class, metadata);
    }

}

