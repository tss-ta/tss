package com.netcracker.tss.web.util;

import com.netcracker.entity.helper.ReportFilter;
import com.netcracker.report.container.MultipurposeValue;
import com.netcracker.report.mapper.TypeConverter;
import com.netcracker.report.mapper.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyrylo Berehovyi
 */
public class ReportFilterParser {

    private static final Map<Integer, TypeConverter> typeConverterMap = new HashMap<>();

    static {
        addConverter(new IntegerTypeConverter());
        addConverter(new LongTypeConverter());
        addConverter(new StringTypeConverter());
        addConverter(new DoubleTypeConverter());
        addConverter(new BooleanTypeConverter());
        addConverter(new TimestampTypeConverter());
    }

    public static final int DATA_TYPE_BOOLEAN = 2;

    public ReportFilter parse(HttpServletRequest request) {
        ReportFilter filter = null;
        Integer amount = RequestParameterParser.parseInteger(request,
                RequestParameter.REPORT_FILTER_CRITERIA_AMOUNT.getValue());

        if (amount == null) {
            return null;
        }

        return generateReportFilterFromRequest(amount, request);
    }

    private ReportFilter generateReportFilterFromRequest(int amount, HttpServletRequest request) {
        ReportFilter filter = new ReportFilter(amount);
        MultipurposeValue temp;
        String name;
        Integer typeIndex;
        for (int i = 1; i <= amount; i++) {
            name = RequestParameter.REPORT_FILTER_CRITERION_NAME_PREFIX.getValue() + i;
            typeIndex = RequestParameterParser.parseInteger(request, RequestParameter.REPORT_FILTER_CRITERION_TYPE_PREFIX.getValue() + i);
            temp = parseCriterionValue(request, name, typeIndex);

            System.out.println("TEMP: " + temp);

            filter.getCriteria().add(temp);
        }
        return filter;
    }

    private MultipurposeValue parseCriterionValue(HttpServletRequest request, String name, Integer type) {
        String unparsedValue = request.getParameter(name);

        System.out.println("Name: " + name);
        System.out.println("Type: " + type);

        if (unparsedValue == null && type != DATA_TYPE_BOOLEAN) {
            return null;
        }

        return typeConverterMap.get(type).convert(unparsedValue);
    }

    private static void addConverter(TypeConverter converter) {
        typeConverterMap.put(converter.type().ordinal(), converter);
    }
}
