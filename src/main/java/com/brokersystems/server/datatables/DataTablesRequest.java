package com.brokersystems.server.datatables;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;


public class DataTablesRequest extends PageRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8268888984655774621L;

	private Integer draw;

    private Search search;

    private List<Column> columns;

    public DataTablesRequest(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    public BooleanExpression searchPredicate(EntityPathBase entity) throws IllegalAccessException {
        BooleanExpression predicate = null;
        for (Column column : this.getColumns()) {
            if (column.getSearchable()) {
                final String value = column.getSearch().getValue();
                final String data = column.getData();

                for (Field field : entity.getClass().getDeclaredFields()) {
                    if (field.getName().equals(data)) {
                        predicate = addColumnSearch(entity, predicate, value, field);
                        predicate = addGlobalSearch(entity, predicate, field);
                    }
                }
            }
        }
        return predicate;
    }

    private BooleanExpression addGlobalSearch(EntityPathBase entity, BooleanExpression predicate, Field field) throws IllegalAccessException {
        if (StringUtils.isNotEmpty(search.getValue())) {
            predicate = searchPredicate(entity, predicate, search.getValue(), field);
        }
        return predicate;
    }

    private BooleanExpression addColumnSearch(EntityPathBase entity, BooleanExpression predicate, String value, Field field) throws IllegalAccessException {
        if (StringUtils.isNotEmpty(value)) {
            predicate = searchPredicate(entity, predicate, value, field);
        }
        return predicate;
    }

    private BooleanExpression searchPredicate(EntityPathBase entity, BooleanExpression predicate, String value, Field field) throws IllegalAccessException {
        String search = "%" + value + "%";
        if (field.get(entity) instanceof Path) {
            if (field.get(entity) instanceof StringPath) {
                StringPath ob = (StringPath) field.get(entity);
                if (predicate == null) {
                    predicate = ob.like(search);
                } else {
                    predicate = predicate.or(ob.like(search));
                }
            } else {
                if (field.get(entity) instanceof NumberPath) {
                    NumberPath ob = (NumberPath) field.get(entity);
                    if (predicate == null) {
                        predicate = ob.eq(search);
                    } else {
                        predicate.or(ob.eq(search));
                    }
                }
            }
        }
        return predicate;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

}
