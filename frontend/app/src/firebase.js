import firebase from "firebase/app"
import "firebase/auth"

const app = firebase.initializeApp({
  apiKey: "AIzaSyDYkWptgeI84Zqy5RQD8zc4YG3q8VxyE70",
  authDomain: "uefo-309cc.firebaseapp.com",
  projectId: "uefo-309cc",
  storageBucket: "uefo-309cc.appspot.com",
  messagingSenderId: "531298380186",
  appId: "1:531298380186:web:bdf60e11c4ea7d73da7223"
})

export const auth = app.auth()
export default app
