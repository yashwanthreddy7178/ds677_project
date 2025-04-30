package com.kornievich.selectionCommition.dao;

import com.kornievich.selectionCommition.entity.EntrantInQueueModal;
import com.kornievich.selectionCommition.exception.DAOException;

import java.util.ArrayList;

public interface IRequest {
    public ArrayList<EntrantInQueueModal> allEntrantsScoreBySpecialty(int idSpecialty) throws DAOException ;
}
