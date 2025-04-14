package com.sharipov.topuch.domain.service;

import com.sharipov.topuch.domain.entity.Report;

import java.util.List;

public interface ReportService {

    List<Report> getAllReports();

    Report createReport(Report report);

    void deleteReportById(Long id);



}
