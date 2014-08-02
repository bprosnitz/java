// This file was auto-generated by the veyron vdl tool.
// Source: identity.vdl
package com.veyron.services.identity;

/**
 * OAuthBlesser exchanges the provided authorization code for an email addres
 * from an OAuth-based identity provider and uses the email address as the
 * name to bless the client with.
 * 
 * The redirect URL used to obtain the authorization code must also be
 * provided in order to ensure a successful exchange.
 */

public interface OAuthBlesser  {

    
    

    
    // TODO(ashankar,toddw): Once the "OneOf" type becomes available in VDL,
// then the "any" should be replaced by:
// OneOf<wire.ChainPublicID, []wire.ChainPublicID>
// where wire is from:
// import "veyron2/security/wire"

    public java.lang.Object bless(final com.veyron2.ipc.Context context, final java.lang.String authcode, final java.lang.String redirecturl) throws com.veyron2.ipc.VeyronException;
    public java.lang.Object bless(final com.veyron2.ipc.Context context, final java.lang.String authcode, final java.lang.String redirecturl, final com.veyron2.Options veyronOpts) throws com.veyron2.ipc.VeyronException;

}