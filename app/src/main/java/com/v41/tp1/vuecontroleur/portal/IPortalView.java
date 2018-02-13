package com.v41.tp1.vuecontroleur.portal;

import com.v41.tp1.modele.portal.IPortalModel;

public interface IPortalView
{
	/**
	 * Notifies the view with the updated model
	 * @param model The model
	 */
	void notify(IPortalModel model);
}
