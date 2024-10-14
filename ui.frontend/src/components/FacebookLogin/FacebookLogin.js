import React, { useEffect } from 'react';
import FacebookLogin from 'react-facebook-login';
//require ('./facebookSDK.js');

const FacebookAuth = () => {

const responseFacebook = (response) => {
   const { accessToken } = response;
   fetch('/bin/facebook-login', {
      method: 'POST',
      headers: {
         'Content-Type': 'application/json',
      },
      body: JSON.stringify({ accessToken }),
   })
   .then(response => response.json())
   .then(data => {
      console.log('Token processed:', data);
   })
   .catch((error) => {
      console.error('Error:', error);
   });
};

  return (
    <div>
      <h2>Login with Facebook</h2>
      <FacebookLogin
        appId="1736603413768011"  // Replace with your Facebook App ID
        autoLoad={true}
        fields="name,picture"
        callback={responseFacebook}
        icon="fa-facebook"
      />
    </div>
  );
};

export default FacebookAuth;
