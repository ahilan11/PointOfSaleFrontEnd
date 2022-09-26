/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carolsboutique.clientpos.email.service;

import com.carolsboutique.clientpos.email.model.Email;

/**
 *
 * @author nicad
 */
public interface iEmailService {
    String sendEmailRecipt(String toEmail);
    String sendEmailNotifiction(String toEmail);
    String addEmail(Email email);
}
