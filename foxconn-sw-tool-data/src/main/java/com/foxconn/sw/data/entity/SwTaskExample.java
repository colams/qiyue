package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SwTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SwTaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTopCategoryIsNull() {
            addCriterion("top_category is null");
            return (Criteria) this;
        }

        public Criteria andTopCategoryIsNotNull() {
            addCriterion("top_category is not null");
            return (Criteria) this;
        }

        public Criteria andTopCategoryEqualTo(String value) {
            addCriterion("top_category =", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryNotEqualTo(String value) {
            addCriterion("top_category <>", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryGreaterThan(String value) {
            addCriterion("top_category >", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("top_category >=", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryLessThan(String value) {
            addCriterion("top_category <", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryLessThanOrEqualTo(String value) {
            addCriterion("top_category <=", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryLike(String value) {
            addCriterion("top_category like", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryNotLike(String value) {
            addCriterion("top_category not like", value, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryIn(List<String> values) {
            addCriterion("top_category in", values, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryNotIn(List<String> values) {
            addCriterion("top_category not in", values, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryBetween(String value1, String value2) {
            addCriterion("top_category between", value1, value2, "topCategory");
            return (Criteria) this;
        }

        public Criteria andTopCategoryNotBetween(String value1, String value2) {
            addCriterion("top_category not between", value1, value2, "topCategory");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTopProjectIsNull() {
            addCriterion("top_project is null");
            return (Criteria) this;
        }

        public Criteria andTopProjectIsNotNull() {
            addCriterion("top_project is not null");
            return (Criteria) this;
        }

        public Criteria andTopProjectEqualTo(String value) {
            addCriterion("top_project =", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectNotEqualTo(String value) {
            addCriterion("top_project <>", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectGreaterThan(String value) {
            addCriterion("top_project >", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectGreaterThanOrEqualTo(String value) {
            addCriterion("top_project >=", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectLessThan(String value) {
            addCriterion("top_project <", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectLessThanOrEqualTo(String value) {
            addCriterion("top_project <=", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectLike(String value) {
            addCriterion("top_project like", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectNotLike(String value) {
            addCriterion("top_project not like", value, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectIn(List<String> values) {
            addCriterion("top_project in", values, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectNotIn(List<String> values) {
            addCriterion("top_project not in", values, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectBetween(String value1, String value2) {
            addCriterion("top_project between", value1, value2, "topProject");
            return (Criteria) this;
        }

        public Criteria andTopProjectNotBetween(String value1, String value2) {
            addCriterion("top_project not between", value1, value2, "topProject");
            return (Criteria) this;
        }

        public Criteria andProjectIsNull() {
            addCriterion("project is null");
            return (Criteria) this;
        }

        public Criteria andProjectIsNotNull() {
            addCriterion("project is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEqualTo(String value) {
            addCriterion("project =", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotEqualTo(String value) {
            addCriterion("project <>", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThan(String value) {
            addCriterion("project >", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThanOrEqualTo(String value) {
            addCriterion("project >=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThan(String value) {
            addCriterion("project <", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThanOrEqualTo(String value) {
            addCriterion("project <=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLike(String value) {
            addCriterion("project like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotLike(String value) {
            addCriterion("project not like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectIn(List<String> values) {
            addCriterion("project in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotIn(List<String> values) {
            addCriterion("project not in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectBetween(String value1, String value2) {
            addCriterion("project between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotBetween(String value1, String value2) {
            addCriterion("project not between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andProgressPercentIsNull() {
            addCriterion("progress_percent is null");
            return (Criteria) this;
        }

        public Criteria andProgressPercentIsNotNull() {
            addCriterion("progress_percent is not null");
            return (Criteria) this;
        }

        public Criteria andProgressPercentEqualTo(Integer value) {
            addCriterion("progress_percent =", value, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentNotEqualTo(Integer value) {
            addCriterion("progress_percent <>", value, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentGreaterThan(Integer value) {
            addCriterion("progress_percent >", value, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("progress_percent >=", value, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentLessThan(Integer value) {
            addCriterion("progress_percent <", value, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentLessThanOrEqualTo(Integer value) {
            addCriterion("progress_percent <=", value, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentIn(List<Integer> values) {
            addCriterion("progress_percent in", values, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentNotIn(List<Integer> values) {
            addCriterion("progress_percent not in", values, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentBetween(Integer value1, Integer value2) {
            addCriterion("progress_percent between", value1, value2, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andProgressPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("progress_percent not between", value1, value2, "progressPercent");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andHandleStatusIsNull() {
            addCriterion("handle_status is null");
            return (Criteria) this;
        }

        public Criteria andHandleStatusIsNotNull() {
            addCriterion("handle_status is not null");
            return (Criteria) this;
        }

        public Criteria andHandleStatusEqualTo(Integer value) {
            addCriterion("handle_status =", value, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusNotEqualTo(Integer value) {
            addCriterion("handle_status <>", value, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusGreaterThan(Integer value) {
            addCriterion("handle_status >", value, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("handle_status >=", value, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusLessThan(Integer value) {
            addCriterion("handle_status <", value, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusLessThanOrEqualTo(Integer value) {
            addCriterion("handle_status <=", value, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusIn(List<Integer> values) {
            addCriterion("handle_status in", values, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusNotIn(List<Integer> values) {
            addCriterion("handle_status not in", values, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusBetween(Integer value1, Integer value2) {
            addCriterion("handle_status between", value1, value2, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andHandleStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("handle_status not between", value1, value2, "handleStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusIsNull() {
            addCriterion("proposer_status is null");
            return (Criteria) this;
        }

        public Criteria andProposerStatusIsNotNull() {
            addCriterion("proposer_status is not null");
            return (Criteria) this;
        }

        public Criteria andProposerStatusEqualTo(Integer value) {
            addCriterion("proposer_status =", value, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusNotEqualTo(Integer value) {
            addCriterion("proposer_status <>", value, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusGreaterThan(Integer value) {
            addCriterion("proposer_status >", value, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("proposer_status >=", value, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusLessThan(Integer value) {
            addCriterion("proposer_status <", value, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusLessThanOrEqualTo(Integer value) {
            addCriterion("proposer_status <=", value, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusIn(List<Integer> values) {
            addCriterion("proposer_status in", values, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusNotIn(List<Integer> values) {
            addCriterion("proposer_status not in", values, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusBetween(Integer value1, Integer value2) {
            addCriterion("proposer_status between", value1, value2, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("proposer_status not between", value1, value2, "proposerStatus");
            return (Criteria) this;
        }

        public Criteria andProposerEidIsNull() {
            addCriterion("proposer_eid is null");
            return (Criteria) this;
        }

        public Criteria andProposerEidIsNotNull() {
            addCriterion("proposer_eid is not null");
            return (Criteria) this;
        }

        public Criteria andProposerEidEqualTo(String value) {
            addCriterion("proposer_eid =", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidNotEqualTo(String value) {
            addCriterion("proposer_eid <>", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidGreaterThan(String value) {
            addCriterion("proposer_eid >", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidGreaterThanOrEqualTo(String value) {
            addCriterion("proposer_eid >=", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidLessThan(String value) {
            addCriterion("proposer_eid <", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidLessThanOrEqualTo(String value) {
            addCriterion("proposer_eid <=", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidLike(String value) {
            addCriterion("proposer_eid like", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidNotLike(String value) {
            addCriterion("proposer_eid not like", value, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidIn(List<String> values) {
            addCriterion("proposer_eid in", values, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidNotIn(List<String> values) {
            addCriterion("proposer_eid not in", values, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidBetween(String value1, String value2) {
            addCriterion("proposer_eid between", value1, value2, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andProposerEidNotBetween(String value1, String value2) {
            addCriterion("proposer_eid not between", value1, value2, "proposerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidIsNull() {
            addCriterion("manager_eid is null");
            return (Criteria) this;
        }

        public Criteria andManagerEidIsNotNull() {
            addCriterion("manager_eid is not null");
            return (Criteria) this;
        }

        public Criteria andManagerEidEqualTo(String value) {
            addCriterion("manager_eid =", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidNotEqualTo(String value) {
            addCriterion("manager_eid <>", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidGreaterThan(String value) {
            addCriterion("manager_eid >", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidGreaterThanOrEqualTo(String value) {
            addCriterion("manager_eid >=", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidLessThan(String value) {
            addCriterion("manager_eid <", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidLessThanOrEqualTo(String value) {
            addCriterion("manager_eid <=", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidLike(String value) {
            addCriterion("manager_eid like", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidNotLike(String value) {
            addCriterion("manager_eid not like", value, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidIn(List<String> values) {
            addCriterion("manager_eid in", values, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidNotIn(List<String> values) {
            addCriterion("manager_eid not in", values, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidBetween(String value1, String value2) {
            addCriterion("manager_eid between", value1, value2, "managerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidNotBetween(String value1, String value2) {
            addCriterion("manager_eid not between", value1, value2, "managerEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidIsNull() {
            addCriterion("handle_eid is null");
            return (Criteria) this;
        }

        public Criteria andHandleEidIsNotNull() {
            addCriterion("handle_eid is not null");
            return (Criteria) this;
        }

        public Criteria andHandleEidEqualTo(String value) {
            addCriterion("handle_eid =", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidNotEqualTo(String value) {
            addCriterion("handle_eid <>", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidGreaterThan(String value) {
            addCriterion("handle_eid >", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidGreaterThanOrEqualTo(String value) {
            addCriterion("handle_eid >=", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidLessThan(String value) {
            addCriterion("handle_eid <", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidLessThanOrEqualTo(String value) {
            addCriterion("handle_eid <=", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidLike(String value) {
            addCriterion("handle_eid like", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidNotLike(String value) {
            addCriterion("handle_eid not like", value, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidIn(List<String> values) {
            addCriterion("handle_eid in", values, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidNotIn(List<String> values) {
            addCriterion("handle_eid not in", values, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidBetween(String value1, String value2) {
            addCriterion("handle_eid between", value1, value2, "handleEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidNotBetween(String value1, String value2) {
            addCriterion("handle_eid not between", value1, value2, "handleEid");
            return (Criteria) this;
        }

        public Criteria andDeadLineIsNull() {
            addCriterion("dead_line is null");
            return (Criteria) this;
        }

        public Criteria andDeadLineIsNotNull() {
            addCriterion("dead_line is not null");
            return (Criteria) this;
        }

        public Criteria andDeadLineEqualTo(String value) {
            addCriterion("dead_line =", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineNotEqualTo(String value) {
            addCriterion("dead_line <>", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineGreaterThan(String value) {
            addCriterion("dead_line >", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineGreaterThanOrEqualTo(String value) {
            addCriterion("dead_line >=", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineLessThan(String value) {
            addCriterion("dead_line <", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineLessThanOrEqualTo(String value) {
            addCriterion("dead_line <=", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineLike(String value) {
            addCriterion("dead_line like", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineNotLike(String value) {
            addCriterion("dead_line not like", value, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineIn(List<String> values) {
            addCriterion("dead_line in", values, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineNotIn(List<String> values) {
            addCriterion("dead_line not in", values, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineBetween(String value1, String value2) {
            addCriterion("dead_line between", value1, value2, "deadLine");
            return (Criteria) this;
        }

        public Criteria andDeadLineNotBetween(String value1, String value2) {
            addCriterion("dead_line not between", value1, value2, "deadLine");
            return (Criteria) this;
        }

        public Criteria andResourceIdsIsNull() {
            addCriterion("resource_ids is null");
            return (Criteria) this;
        }

        public Criteria andResourceIdsIsNotNull() {
            addCriterion("resource_ids is not null");
            return (Criteria) this;
        }

        public Criteria andResourceIdsEqualTo(String value) {
            addCriterion("resource_ids =", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsNotEqualTo(String value) {
            addCriterion("resource_ids <>", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsGreaterThan(String value) {
            addCriterion("resource_ids >", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsGreaterThanOrEqualTo(String value) {
            addCriterion("resource_ids >=", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsLessThan(String value) {
            addCriterion("resource_ids <", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsLessThanOrEqualTo(String value) {
            addCriterion("resource_ids <=", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsLike(String value) {
            addCriterion("resource_ids like", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsNotLike(String value) {
            addCriterion("resource_ids not like", value, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsIn(List<String> values) {
            addCriterion("resource_ids in", values, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsNotIn(List<String> values) {
            addCriterion("resource_ids not in", values, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsBetween(String value1, String value2) {
            addCriterion("resource_ids between", value1, value2, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andResourceIdsNotBetween(String value1, String value2) {
            addCriterion("resource_ids not between", value1, value2, "resourceIds");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("start_date like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("start_date not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("end_date like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("end_date not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeIsNull() {
            addCriterion("datetime_lastchange is null");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeIsNotNull() {
            addCriterion("datetime_lastchange is not null");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange =", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeNotEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange <>", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeGreaterThan(LocalDateTime value) {
            addCriterion("datetime_lastchange >", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange >=", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeLessThan(LocalDateTime value) {
            addCriterion("datetime_lastchange <", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("datetime_lastchange <=", value, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeIn(List<LocalDateTime> values) {
            addCriterion("datetime_lastchange in", values, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeNotIn(List<LocalDateTime> values) {
            addCriterion("datetime_lastchange not in", values, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("datetime_lastchange between", value1, value2, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andDatetimeLastchangeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("datetime_lastchange not between", value1, value2, "datetimeLastchange");
            return (Criteria) this;
        }

        public Criteria andTopCategoryLikeInsensitive(String value) {
            addCriterion("upper(top_category) like", value.toUpperCase(), "topCategory");
            return (Criteria) this;
        }

        public Criteria andCategoryLikeInsensitive(String value) {
            addCriterion("upper(category) like", value.toUpperCase(), "category");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andTopProjectLikeInsensitive(String value) {
            addCriterion("upper(top_project) like", value.toUpperCase(), "topProject");
            return (Criteria) this;
        }

        public Criteria andProjectLikeInsensitive(String value) {
            addCriterion("upper(project) like", value.toUpperCase(), "project");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andLevelLikeInsensitive(String value) {
            addCriterion("upper(level) like", value.toUpperCase(), "level");
            return (Criteria) this;
        }

        public Criteria andProposerEidLikeInsensitive(String value) {
            addCriterion("upper(proposer_eid) like", value.toUpperCase(), "proposerEid");
            return (Criteria) this;
        }

        public Criteria andManagerEidLikeInsensitive(String value) {
            addCriterion("upper(manager_eid) like", value.toUpperCase(), "managerEid");
            return (Criteria) this;
        }

        public Criteria andHandleEidLikeInsensitive(String value) {
            addCriterion("upper(handle_eid) like", value.toUpperCase(), "handleEid");
            return (Criteria) this;
        }

        public Criteria andDeadLineLikeInsensitive(String value) {
            addCriterion("upper(dead_line) like", value.toUpperCase(), "deadLine");
            return (Criteria) this;
        }

        public Criteria andResourceIdsLikeInsensitive(String value) {
            addCriterion("upper(resource_ids) like", value.toUpperCase(), "resourceIds");
            return (Criteria) this;
        }

        public Criteria andStartDateLikeInsensitive(String value) {
            addCriterion("upper(start_date) like", value.toUpperCase(), "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLikeInsensitive(String value) {
            addCriterion("upper(end_date) like", value.toUpperCase(), "endDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}