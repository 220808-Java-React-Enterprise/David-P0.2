package com.revature.water_world.services;

import com.revature.water_world.daos.InfoDAO;
import com.revature.water_world.models.AccountInfo;

import java.util.List;

public class InfoService {
    private final InfoDAO infoDAO;

    public InfoService(InfoDAO infoDAO) {
        this.infoDAO = infoDAO;
    }

    public void saveAcInfo(AccountInfo info) {
        infoDAO.saveInfo(info);
    }


}
