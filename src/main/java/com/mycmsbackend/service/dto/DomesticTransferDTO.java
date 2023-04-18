package com.mycmsbackend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//  @RequiredArgsConstructor create constructor for all fields that are either @NonNull or final.
public class DomesticTransferDTO {

    private Long id;
    private String domesticTransferTrxId;
    private String domesticTransferTrxName;
    private String domesticTransferTrxEmailRecipient;
    private double domesticTransferTrxAmount;
}
