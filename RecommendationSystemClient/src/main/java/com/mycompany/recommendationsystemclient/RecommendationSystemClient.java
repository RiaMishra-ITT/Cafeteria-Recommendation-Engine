/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recommendationsystemclient;

import authentication.Authentication;


public class RecommendationSystemClient {

    public static void main(String[] args) {
        Client client = new Client();
        Authentication authentication = new Authentication(client);
        authentication.login();
    }
}
