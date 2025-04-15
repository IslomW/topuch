package com.sharipov.topuch.application.converter;

import com.sharipov.topuch.application.dto.request.ReportRequestDTO;
import com.sharipov.topuch.application.dto.response.ReportResponseDTO;
import com.sharipov.topuch.domain.entity.Report;

import java.util.List;

public interface ReportMapper {

    ReportResponseDTO toDto(Report report);

    Report toEntity(ReportRequestDTO requestDTO);

    List<ReportResponseDTO> toDtoList(List<Report> reports);

}
