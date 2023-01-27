import {RemoteApp} from '@eclipse-scout/core';
import * as business from './index';

Object.assign({}, business); // Use import so that it is not marked as unused

new RemoteApp().init();
