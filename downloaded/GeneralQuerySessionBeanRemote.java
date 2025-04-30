package com.csaszi.generalQuery;

import javax.ejb.Remote;
import java.util.EnumMap;
import java.util.List;

@Remote
public interface GeneralQuerySessionBeanRemote<T> {

    List<Object[]> getReportData(Integer vnCode);

    Long getReportSumOfWorkData(Integer vnCode);

    List<Object[]> selectQueryForResultListWithMoreColumnWithoutParameter(String query);

    List<T> selectQueryForResultListWithoutParameter(String query);

    Object selectQueryForSingleResultWithoutParameter(String query);

    Object selectQueryReturnWithSingleResult(String query, EnumMap parameterMap);

    List<T> selectQueryReturnWithList(String query, EnumMap parameterMap);

    List<Object[]> selectQueryReturnWithListBlock(String query, EnumMap parameterMap);

    void setQueryParameters(String query);
}
