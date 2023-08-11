package com.mifos.repositories

import com.mifos.objects.runreports.FullParameterListResponse
import rx.Observable

interface ReportDetailRepository {

    fun getReportFullParameterList(
        reportName: String?, parameterType: Boolean
    ): Observable<FullParameterListResponse>

    fun getReportParameterDetails(
        parameterName: String?, parameterType: Boolean
    ): Observable<FullParameterListResponse>

    fun getRunReportOffices(
        parameterName: String?, officeId: Int, parameterType: Boolean
    ): Observable<FullParameterListResponse>

}