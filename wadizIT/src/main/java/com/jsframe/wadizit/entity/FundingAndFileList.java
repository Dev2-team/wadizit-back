package com.jsframe.wadizit.entity;

import lombok.Data;

import java.util.List;

@Data
public class FundingAndFileList {
    private Funding funding;
    private List<FundingFile> fundingFileList;
}
