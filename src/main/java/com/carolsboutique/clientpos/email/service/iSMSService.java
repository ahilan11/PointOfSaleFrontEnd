/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.email.service;

import com.carolsboutique.clientpos.email.model.SMS;
import java.time.LocalDateTime;

/**
 *
 * @author nicad
 */
public interface iSMSService {
    String sendSMS(SMS sms);
}
