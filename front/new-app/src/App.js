// import 'devextreme/dist/css/dx.common.css';
// import 'devextreme/dist/css/dx.light.css';
import './node_modules/themes/generated/theme.base.css';
import './node_modules/themes/generated/theme.additional.css';

import React, {useContext} from "react";

import {CurrentUserProvider} from "./node_modules/contexts/currentUser"
import CurrentUserChecker  from './node_modules/components/currentUserChecker'
import NavigationDrawer from './node_modules/components/NavBar/DraveNavBar/NavigationDrawer';
import './dx-styles.scss';
import useAuth from './node_modules/hooks/useAuth'
import { useScreenSizeClass } from './node_modules/utils/media-query';
// import FAQ from './node_modules/components/faq/faq'
import ScrollToTop from './node_modules/components/scrollToTop/scrollToTop'
import Faq from './node_modules/components/faq_/faqs'


function App() {
const auth = useAuth()
console.log('аутентификация - ', auth)
  return <NavigationDrawer />;
}

export default function Root(){
  const screenSizeClass = useScreenSizeClass();
   return(
   <>
      <CurrentUserProvider>
        <CurrentUserChecker>
        <div className={`app ${screenSizeClass}`}>   
             <ScrollToTop/>     
              <App />
        </div>
        </CurrentUserChecker>
      </CurrentUserProvider>
    </>)
  
}
