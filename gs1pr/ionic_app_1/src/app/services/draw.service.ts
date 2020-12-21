import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Draw, DrawList } from '../interfaces/interfaces';

const headers = new HttpHeaders({
  'Content-Type': 'application/json',
});
const addDrawURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/addDraw';
const addRouteURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/addRoute';
const getDrawURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/draw/';
const getAllDrawURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/draws/';
const getRoutesURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/routes/';
const likedURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/isUserLikeRoute';
const likeURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/addLike';
const delURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/route/';
const topDrawURL = 'http://drawyourroute.servemp3.com:8080/ServiceDrawYourRoute/resources/userResource/routesMoreLikes/';

@Injectable({
  providedIn: 'root'
})
export class DrawService {

  constructor(private httpClient: HttpClient) { }

  addDraw(data){
    return this.httpClient.post(addDrawURL, data, {headers});
  }

  addRoute(data){
    return this.httpClient.post(addRouteURL, data, {headers}); 
  }

  getDraw(data){
    return this.httpClient.get<Draw>(getDrawURL+data, {headers});
  }

  getDraws(id){
    return this.httpClient.get<DrawList>(getAllDrawURL, {headers});
  }

  getRoutes(id){
    return this.httpClient.get<DrawList>(getRoutesURL+id, {headers});
  }

  getLiked(idU, idR){
    return this.httpClient.get(likedURL+'/'+idR+'/'+idU, {headers});
  }

  addLike(idU, idR){
    const data = {
      idLoggedUser: idU,
      idRoute: idR
    }
    return this.httpClient.post(likeURL, data, {headers});
  }

  delRoute(id){
    return this.httpClient.delete(delURL+id, {headers});
  }

  getTopRoutes(){
    return this.httpClient.get<DrawList>(topDrawURL, {headers});
  }
}
