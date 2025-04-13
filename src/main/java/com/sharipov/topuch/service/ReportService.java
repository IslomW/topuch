package com.sharipov.topuch.service;

import com.sharipov.topuch.entity.Report;

import java.util.List;

public interface ReportService {

    List<Report> getAllReports();

    Report createReport(Report report);

    void deleteReportById(Long id);



}
