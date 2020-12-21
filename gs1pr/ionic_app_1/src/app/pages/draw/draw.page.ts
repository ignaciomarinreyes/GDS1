import { Component, OnInit } from '@angular/core';
import { Platform, PopoverController } from '@ionic/angular';

import { AddPopOverPage } from '../add-pop-over/add-pop-over.page';

declare const google: any;
/// <reference types="@types/googlemaps" />

@Component({
  selector: 'app-draw',
  templateUrl: './draw.page.html',
  styleUrls: ['./draw.page.scss'],
})
export class DrawPage implements OnInit {
  lat: number = 51.678418;
  lng: number = 7.809007;
  height = 0;
  zoom = 18;

  drawingManager;
  drawPolygon;
  
  mapInstance;

  points: {
    'lat': number,
    'lng': number
  }[] = [];

  constructor(public platform: Platform,
    private popoverController: PopoverController) {
    this.height = platform.height() - platform.height()*0.5;
  }

  ngOnInit() {
    navigator.geolocation.getCurrentPosition(
      (position: Position) =>{
        const pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
        this.lat = pos.lat;
        this.lng = pos.lng;
      });
  }

  onMapReady(map){
    this.mapInstance = map;
    this.initDrawing(map);
  }

  initDrawing(map: any){
    const options = {
      drawingControl: false,
      drawingControlOptions: {
        drawingModes: ["polygon"]
      },
      polygonOptions: {
        draggable: true,
        editable: true,
      },
      drawingMode: google.maps.drawing.OverlayType.POLYGON
    };

    this.drawingManager = new google.maps.drawing.DrawingManager(options);
    this.drawingManager.setMap(map);
    this.drawingManager.setDrawingMode(null);

    google.maps.event.addListener(
      this.drawingManager,
      'overlaycomplete',
      (event) => {
        this.updateRoutePoints(event.overlay.getPath());
      });
  }

  updateRoutePoints(path){
    this.points = [];
    for (var i = 0; i < path.getLength(); ++i) {
      this.points.push(path.getAt(i).toJSON());
    }
    console.log(this.points);
  }

  dragged(lat, lng, $event: google.maps.MouseEvent){
    console.log(lat +  " "  + lng);
    this.lat = $event.latLng.lat();
    this.lng = $event.latLng.lng();
  }

  setPoint(){
    this.points.push({lat: this.lat, lng: this.lng});
    this.drawPolygon = new google.maps.Polyline({
      path: this.points,
      strokeWeight: 1
    });
    console.log(this.points);
    this.drawPolygon.setMap(this.mapInstance);
  }

  erasePoint(){
    if (this.points.length != 0) {
      this.points.pop();
      this.drawPolygon.setMap(null);
      this.drawPolygon = new google.maps.Polyline({
        path: this.points,
        strokeWeight: 1
      });
      console.log(this.points);
      this.drawPolygon.setMap(this.mapInstance);
    }
  }

  async endDraw(event: any){
  
    const modal = await this.popoverController.create({
      component: AddPopOverPage,
      componentProps: {
        points: this.points,
        drawId: null
      },
      translucent: true,
    }); 
    await modal.present();

    const resp = await modal.onDidDismiss();
  }

}
