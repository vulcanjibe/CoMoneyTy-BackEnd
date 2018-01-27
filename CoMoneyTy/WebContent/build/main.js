webpackJsonp([0],{

/***/ 100:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return WalkthroughPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__login_login__ = __webpack_require__(173);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__signup_signup__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__cmy_liste_event_cmy_liste_event__ = __webpack_require__(71);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var WalkthroughPage = (function () {
    function WalkthroughPage(nav, restangular, constante) {
        this.nav = nav;
        this.restangular = restangular;
        this.constante = constante;
        this.lastSlide = false;
    }
    WalkthroughPage.prototype.skipIntro = function () {
        // You can skip to main app
        // this.nav.setRoot(TabsNavigationPage);
        // Or you can skip to last slide (login/signup slide)
        this.lastSlide = true;
        this.slider.slideTo(this.slider.length());
    };
    WalkthroughPage.prototype.skipDevIntro = function () {
        var _this = this;
        localStorage.removeItem('id_token');
        localStorage.removeItem('user');
        this.userDev = new __WEBPACK_IMPORTED_MODULE_5__cmy_model_cmy_model__["l" /* User */]();
        this.userDev.login = "Herve";
        this.userDev.password = "CoMoneyTy";
        this.restangular.one("user").post("login", this.userDev).subscribe(function (resp) {
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            _this.constante.login(resp.user);
            _this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_6__cmy_liste_event_cmy_liste_event__["a" /* ListeEvent */]);
        }, function (errorResponse) {
            console.log("Error with status code", errorResponse.status);
        });
    };
    WalkthroughPage.prototype.onSlideChanged = function () {
        // If it's the last slide, then hide the 'Skip' button on the header
        this.lastSlide = this.slider.isEnd();
    };
    WalkthroughPage.prototype.goToLogin = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_2__login_login__["a" /* LoginPage */]);
    };
    WalkthroughPage.prototype.goToSignup = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_3__signup_signup__["a" /* SignupPage */]);
    };
    WalkthroughPage.prototype.fileChange = function (event) {
        var fileList = event.target.files;
        var file = fileList[0];
        var fd = new FormData();
        fd.append('file', file);
        this.restangular.one('event').post('upload', fd).subscribe(function (resp) {
            console.log(resp);
        }, function (error) {
            console.log(error);
        });
    };
    return WalkthroughPage;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('slider'),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["m" /* Slides */])
], WalkthroughPage.prototype, "slider", void 0);
WalkthroughPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'walkthrough-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\walkthrough\walkthrough.html"*/'<ion-header class="walkthrough-header">\n  <ion-toolbar>\n    <ion-buttons end>\n      <button ion-button class="skip-button" (tap)="skipIntro()" [hidden]="lastSlide">Skip</button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class="walkthrough-content">\n  <ion-slides #slider (ionSlideDidChange)="onSlideChanged()" pager="true">\n    <ion-slide class="slide-1">\n      <ion-row class="intro-image-row">\n        <ion-col center width-64 no-padding>\n          <preload-image [ratio]="{w:1, h:1}" src="./assets/images/LogoCoMoneyTy.png" alt="app logo" title="CoMoneyTy"></preload-image>\n        </ion-col>\n      </ion-row>\n\n      <h2 class="main-title" (press)="skipDevIntro()">Bienvenue!</h2>\n\n      <p class="intro-text">\n        L\'argent facile au quotidien avec <b>CoMoneyTy</b>\n      </p>\n\n    </ion-slide>\n    <ion-slide class="slide-2">\n      <ion-row class="intro-image-row">\n        <ion-col center width-64 no-padding>\n          <preload-image [ratio]="{w:1, h:1}" src="./assets/images/slide-2-img.png" alt="slide 2 img" title="CoMoneyTy"></preload-image>\n        </ion-col>\n      </ion-row>\n      <h2 class="main-title">C\'est facile!</h2>\n      <p class="intro-text">\n        Ne perdez plus votre temps\n      </p>\n      <p class="intro-text">\n        A vous demander qui vous doit de l\'argent,\n      </p>\n      <p class="intro-text">\n        ou qui vous ne doit!!!\n      </p>\n    </ion-slide>\n    <ion-slide class="slide-3">\n      <ion-row class="intro-image-row">\n        <ion-col center width-64 no-padding>\n          <preload-image [ratio]="{w:1, h:1}" src="./assets/images/slide-3-img.png" alt="slide 3 img" title="ion2FullApp"></preload-image>\n        </ion-col>\n      </ion-row>\n      <h2 class="main-title">C\'est parti!</h2>\n      <p class="intro-text">\n        Facilitez vous la vie au quotidien,\n      </p>\n      <p class="intro-text">\n        vous gérez votre argent d\'un click!\n      </p>\n    </ion-slide>\n    <ion-slide class="slide-4">\n      <ion-row class="intro-image-row">\n        <ion-col center width-64 no-padding>\n          <preload-image [ratio]="{w:1, h:1}" src="./assets/images/slide-4-img.png" alt="slide 4 img" title="ion2FullApp"></preload-image>\n        </ion-col>\n      </ion-row>\n      <h2 class="main-title">Restez connecter!</h2>\n      <p class="intro-text">\n        Connectez-vous sur CoMoneyTy\n      </p>\n      <div class="button-bar">\n        <button ion-button primary (tap)="goToLogin()">Log in</button>\n        <button ion-button primary (tap)="goToSignup()">Sign up</button>\n      </div>\n    </ion-slide>\n  </ion-slides>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\walkthrough\walkthrough.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_4_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_5__cmy_model_cmy_model__["a" /* Constante */]])
], WalkthroughPage);

//# sourceMappingURL=walkthrough.js.map

/***/ }),

/***/ 124:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MenuCircular; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return SousMenu; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__pages_cmy_model_cmy_model__ = __webpack_require__(8);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var MenuCircular = (function () {
    function MenuCircular(constante) {
        this.constante = constante;
        this.openMenu = false;
        this.visible = false;
        this.buttons = [];
    }
    MenuCircular.prototype.close = function () {
        this.visible = false;
        //  this.parent.cover.nativeElement.style.display="none";
        this.openMenu = false;
        this.parent.visible = false;
    };
    MenuCircular.prototype.action = function (button) {
        console.log("Go sur " + button.sousMenu.action);
        var fonction = button.sousMenu.action;
        fonction.call(this.parent, this.objet);
    };
    MenuCircular.prototype.config = function (sousmenus) {
        for (var _i = 0, sousmenus_1 = sousmenus; _i < sousmenus_1.length; _i++) {
            var sousmenu = sousmenus_1[_i];
            var button = new Button(sousmenu);
            this.buttons.push(button);
        }
        var i = 0;
        var l = this.buttons.length;
        for (var _a = 0, _b = this.buttons; _a < _b.length; _a++) {
            var button = _b[_a];
            button.left = (50 - 35 * Math.cos(-0.5 * Math.PI - 2 * (1 / l) * i * Math.PI)).toFixed(4) + "%";
            button.top = (50 + 35 * Math.sin(-0.5 * Math.PI - 2 * (1 / l) * i * Math.PI)).toFixed(4) + "%";
            i++;
        }
    };
    MenuCircular.prototype.show = function (parent, obj, urlPhoto) {
        this.parent = parent;
        this.visible = true;
        //   this.parent.cover.nativeElement.style.display="block";
        this.objet = obj;
        this.photo = urlPhoto;
        this.openMenu = false;
    };
    MenuCircular.prototype.hide = function () {
        this.visible = false;
    };
    MenuCircular.prototype.toggle = function () {
        this.openMenu = !this.openMenu;
    };
    MenuCircular.prototype.blockEvent = function () {
        console.log("Il faut bloquer!!!");
        //    this.parent.cover.nativeElement.style.display="none";
        this.hide();
    };
    return MenuCircular;
}());
MenuCircular = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'menu-circular',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\components\menu-circular\menu-circular.html"*/'\n<div [ngClass]="{\'invisible\':!visible}">\n  <div  [ngClass]="{\'circleClose\':!openMenu,\'circleOpen\':openMenu}">\n    <ion-fab *ngFor="let button of buttons" class="petitMenu"  [ngStyle]="button.getStyle()">\n      <button (tap)="action(button)" ion-fab color="primary">\n        <div class="sousMenuLabel">{{button.sousMenu.libelle}}</div>\n        <ion-icon [name]="button.sousMenu.icon"></ion-icon>\n      </button>\n    </ion-fab>\n  </div>\n  <ion-fab class="centered">\n    <button *ngIf="photo==null" (tap)="toggle()" ion-fab color="primary"><ion-icon name="add"></ion-icon></button>\n  </ion-fab>\n\n  <img class="centered-menu" *ngIf="photo!=null" (tap)="toggle()" [src]=\'constante.getUrlImage(photo)\'>\n\n</div>\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\components\menu-circular\menu-circular.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__pages_cmy_model_cmy_model__["a" /* Constante */]])
], MenuCircular);

var SousMenu = (function () {
    function SousMenu(libelle, action, icon) {
        this.libelle = libelle;
        this.action = action;
        this.icon = icon;
    }
    return SousMenu;
}());

var Button = (function () {
    function Button(sm) {
        this.sousMenu = sm;
    }
    Button.prototype.getStyle = function () {
        return { 'top': this.top, 'left': this.left };
    };
    return Button;
}());
//# sourceMappingURL=menu-circular.js.map

/***/ }),

/***/ 125:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GestionProfile; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(22);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__walkthrough_walkthrough__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__ionic_native_camera__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_crypto_js__ = __webpack_require__(209);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_crypto_js___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_crypto_js__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




//import 'rxjs/Rx';




var GestionProfile = (function () {
    function GestionProfile(nav, modal, camera, loadingCtrl, toastCtrl, alertController, constante, restangular) {
        this.nav = nav;
        this.modal = modal;
        this.camera = camera;
        this.loadingCtrl = loadingCtrl;
        this.toastCtrl = toastCtrl;
        this.alertController = alertController;
        this.constante = constante;
        this.restangular = restangular;
        // make WalkthroughPage the root (or first) page
        this.rootPage = __WEBPACK_IMPORTED_MODULE_3__walkthrough_walkthrough__["a" /* WalkthroughPage */];
        this.imageDataCamera = null;
        this.options = {
            quality: 80,
            sourceType: this.camera.PictureSourceType.CAMERA,
            destinationType: this.camera.DestinationType.DATA_URL,
            encodingType: this.camera.EncodingType.PNG,
            saveToPhotoAlbum: false,
            targetWidth: 200,
            targetHeight: 200
        };
        this.loading = this.loadingCtrl.create();
        this.user = this.constante.user;
        this.settingsForm = new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* FormGroup */]({
            email: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](this.user.email, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].pattern("[a-z0-9.-_]+@[a-z.]+")])),
            phone: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](this.user.phone, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(10), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].pattern("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$")])),
            nom: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](this.user.nom, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5)])),
            prenom: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](this.user.prenom, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(3)])),
            password: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5)])),
            confirm_password: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5)])),
            iban: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](this.user.iban, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required),
            currency: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]("euro"),
            notifications: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](true),
            toogleCodeSecu: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](true),
            codecourt: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](this.user.codecourt, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(4), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].maxLength(4), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].pattern("^[0-9]{4}$")])),
        });
        if (this.user.codecourt == null || this.user.codecourt.length == 0) {
            this.settingsForm.get("toogleCodeSecu").setValue(false);
        }
    }
    GestionProfile.prototype.ionViewDidLoad = function () {
    };
    GestionProfile.prototype.createFileName = function () {
        var d = new Date();
        var n = d.getTime();
        var newFileName = "user/" + this.constante.user.id + "_" + n + ".png";
        return newFileName;
    };
    GestionProfile.prototype.changePhoto = function () {
        var _this = this;
        var alert = this.alertController.create({
            title: 'Modifier votre photo',
            message: "Voulez-vous :",
            buttons: [
                {
                    text: 'Prendre une photo',
                    role: 'cancel',
                    handler: function () {
                        _this.takePhoto().then(function (imageData) {
                            _this.imageDataCamera = "data:image/png;base64," + imageData;
                            var nomFichier = _this.createFileName();
                            _this.user.urlAvatar = nomFichier + "==" + _this.imageDataCamera;
                            // Envoi de la photo
                        }, function (err) {
                            _this.constante.traiteErreur(err, _this);
                        });
                    }
                },
                {
                    text: 'Choisir dans votre galerie',
                    handler: function () {
                        _this.chooseGallery().then(function (imageData) {
                            _this.imageDataCamera = "data:image/png;base64," + imageData;
                            var nomFichier = _this.createFileName();
                            _this.user.urlAvatar = nomFichier + "==" + _this.imageDataCamera;
                        }, function (err) {
                            _this.constante.traiteErreur(err, _this);
                        });
                    }
                },
                {
                    text: 'Ne rien faire',
                    handler: function () {
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    GestionProfile.prototype.takePhoto = function () {
        this.options.sourceType = this.camera.PictureSourceType.CAMERA;
        return this.camera.getPicture(this.options);
    };
    ;
    GestionProfile.prototype.chooseGallery = function () {
        this.options.sourceType = this.camera.PictureSourceType.PHOTOLIBRARY;
        return this.camera.getPicture(this.options);
    };
    ;
    GestionProfile.prototype.logout = function () {
        // navigate to the new page if it is not the current page
        this.constante.user = new __WEBPACK_IMPORTED_MODULE_4__cmy_model_cmy_model__["l" /* User */]();
        this.nav.setRoot(this.rootPage);
    };
    ;
    GestionProfile.prototype.save = function () {
        var _this = this;
        this.loading.present();
        var user = this.restangular.copy(this.user);
        user.route = "user";
        user.password = this.settingsForm.get("password").value;
        var passconf = this.settingsForm.get("confirm_password").value;
        if (passconf != user.password) {
            this.constante.presentToast("Les mots de passe sont différents!");
            this.settingsForm.get("confirm_password").markAsDirty();
            return;
        }
        user.email = this.settingsForm.get("email").value;
        user.phone = this.settingsForm.get("phone").value;
        user.iban = this.settingsForm.get("iban").value;
        user.nom = this.settingsForm.get("nom").value;
        user.prenom = this.settingsForm.get("prenom").value;
        user.codecourt = this.settingsForm.get("codecourt").value;
        if (!this.settingsForm.get("toogleCodeSecu").value) {
            user.codecourt = "";
        }
        user.save().toPromise().then(function (resp) {
            _this.constante.user = resp;
            if (resp.codecourt == null || resp.codecourt.length == 0) {
                localStorage.removeItem("codecourt");
            }
            else {
                var derivedKey = __WEBPACK_IMPORTED_MODULE_7_crypto_js___default.a.PBKDF2(resp.codecourt, "AlwaysTheSameSalt", {
                    keySize: 512 / 32,
                    iterations: 5
                }).toString();
                localStorage.setItem("codecourt", derivedKey);
            }
            _this.loading.dismissAll();
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    return GestionProfile;
}());
GestionProfile = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'gestion-profile',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-gestion-profile\cmy-gestion-profile.html"*/'<ion-header>\n  <ion-navbar>\n    <ion-title>Settings</ion-title>\n    <ion-buttons end [hidden]="!settingsForm.dirty">\n      <button ion-button (tap)="save()">\n        Save\n      </button>\n    </ion-buttons>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="settings-content">\n  <div class="user-image-content">\n    <ion-row no-padding class="user-image-row">\n      <ion-col no-padding width-40>\n        <preload-image *ngIf="imageDataCamera==null" class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(user.urlAvatar)\' alt="aca va la img" title="IMAGE!"></preload-image>\n        <img [src]="imageDataCamera" *ngIf="imageDataCamera" />\n      </ion-col>\n    </ion-row>\n    <h3 class="image-action" (tap)="changePhoto()">Changer la photo de profil</h3>\n  </div>\n\n  <form [formGroup]="settingsForm" class="settings-form">\n    <ion-list class="user-data-content">\n      <ion-row>\n        <ion-col col-6>\n          <ion-item>\n            <ion-label stacked>Nom</ion-label>\n            <ion-input type="text" placeholder="Nom" formControlName="nom"></ion-input>\n          </ion-item>\n        </ion-col>\n        <ion-col  col-6>\n          <ion-item>\n            <ion-label stacked>Prénom</ion-label>\n            <ion-input type="text" placeholder="Prénom" formControlName="prenom"></ion-input>\n          </ion-item>\n        </ion-col>\n      </ion-row>\n\n      <ion-item>\n        <ion-label stacked>Email</ion-label>\n        <ion-input formControlName="email" placeholder="Email..."></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label stacked>Téléphone</ion-label>\n        <ion-input formControlName="phone" placeholder="Phone..."></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-label stacked>Compte</ion-label>\n        <ion-input formControlName="iban" placeholder="iban..."></ion-input>\n      </ion-item>\n    </ion-list>\n\n    <ion-row class="user-preferences-row">\n      <ion-item>\n        <ion-label stacked>Code de sécurité</ion-label>\n      </ion-item>\n      <show-hide-container>\n      <ion-input type="password" placeholder="code" formControlName="codecourt"  show-hide-input></ion-input>\n      </show-hide-container>\n      <ion-toggle formControlName="toogleCodeSecu"></ion-toggle>\n    </ion-row>\n\n\n    <ion-row class="user-preferences-row">\n      <ion-item>\n        <ion-label stacked>Password</ion-label>\n      </ion-item>\n      <show-hide-container>\n        <ion-item>\n          <ion-input type="password" placeholder="Password" formControlName="password" show-hide-input></ion-input>\n        </ion-item>\n      </show-hide-container>\n      <show-hide-container>\n        <ion-item>\n          <ion-input type="password" placeholder="Confirm password" formControlName="confirm_password" show-hide-input></ion-input>\n        </ion-item>\n      </show-hide-container>\n    </ion-row>\n\n\n\n    <ion-row class="user-preferences-row">\n      <span class="radio-tags-label">Currency</span>\n      <ion-list class="radio-tags" radio-group formControlName="currency">\n        <ion-item class="radio-tag">\n          <ion-label>&#36;</ion-label>\n          <ion-radio value="dollar"></ion-radio>\n        </ion-item>\n        <ion-item class="radio-tag">\n          <ion-label>\n            <span>&euro;</span>\n          </ion-label>\n          <ion-radio value="euro"></ion-radio>\n        </ion-item>\n        <ion-item class="radio-tag">\n          <ion-label>\n            <span>&pound;</span>\n          </ion-label>\n          <ion-radio value="pound"></ion-radio>\n        </ion-item>\n      </ion-list>\n    </ion-row>\n\n\n    <ion-list class="switchers-list">\n      <ion-item class="switcher-item">\n        <ion-label>Notifications</ion-label>\n        <ion-toggle formControlName="notifications"></ion-toggle>\n      </ion-item>\n    </ion-list>\n  </form>\n\n\n\n  <button class="alt-button logout-button" ion-button full icon-left (tap)="logout()">\n    <ion-icon name="log-out"></ion-icon>\n    Log out\n  </button>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-gestion-profile\cmy-gestion-profile.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_6__ionic_native_camera__["a" /* Camera */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_4__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"]])
], GestionProfile);

//# sourceMappingURL=cmy-gestion-profile.js.map

/***/ }),

/***/ 173:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(22);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__signup_signup__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__forgot_password_forgot_password__ = __webpack_require__(414);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__facebook_login_service__ = __webpack_require__(212);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__google_login_service__ = __webpack_require__(214);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__privacy_policy_privacy_policy__ = __webpack_require__(220);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10_crypto_js__ = __webpack_require__(209);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10_crypto_js___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_10_crypto_js__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__master_master_home__ = __webpack_require__(415);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};












var LoginPage = (function () {
    function LoginPage(nav, facebookLoginService, googleLoginService, loadingCtrl, restangular, constante) {
        this.nav = nav;
        this.facebookLoginService = facebookLoginService;
        this.googleLoginService = googleLoginService;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.constante = constante;
        this.main_page = { component: __WEBPACK_IMPORTED_MODULE_11__master_master_home__["a" /* MasterHome */] };
        this.user = new __WEBPACK_IMPORTED_MODULE_8__cmy_model_cmy_model__["l" /* User */]();
        this.login = new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* FormGroup */]({
            email: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(3)])),
            password: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5)]))
        });
    }
    LoginPage.prototype.doLogin = function () {
        var _this = this;
        this.user.email = this.login.get('email').value;
        this.user.login = this.login.get('email').value;
        this.user.password = this.login.get('password').value;
        localStorage.removeItem('id_token');
        localStorage.removeItem('user');
        this.restangular.one("user").post("login", this.user).subscribe(function (resp) {
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            if (resp.user.codecourt != null && resp.user.codecourt.length > 0) {
                var derivedKey = __WEBPACK_IMPORTED_MODULE_10_crypto_js___default.a.PBKDF2(resp.user.codecourt, "AlwaysTheSameSalt", {
                    keySize: 512 / 32,
                    iterations: 5
                }).toString();
                localStorage.setItem("codecourt", derivedKey);
            }
            _this.constante.login(resp.user);
            _this.nav.setRoot(_this.main_page.component);
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    LoginPage.prototype.doFacebookLogin = function () {
        this.loading = this.loadingCtrl.create();
        // Here we will check if the user is already logged in because we don't want to ask users to log in each time they open the app
        var env = this;
        this.facebookLoginService.getFacebookUser()
            .then(function (data) {
            // user is previously logged with FB and we have his data we will let him access the app
            // data : name, image,userID
            env.loggue_fb(data);
        }, function (error) {
            //we don't have the user data so we will ask him to log in
            env.facebookLoginService.doFacebookLogin()
                .then(function (data) {
                env.loading.dismiss();
                env.loggue_fb(data);
            }, function (err) {
                env.constante.traiteErreur(err, env);
                env.facebookLoginService.doFacebookLogout().then(function (response) {
                    console.log("cookie facebook effacé");
                }, function (error) {
                });
            });
        });
    };
    ;
    LoginPage.prototype.loggue_fb = function (data) {
        var _this = this;
        var user = new __WEBPACK_IMPORTED_MODULE_8__cmy_model_cmy_model__["l" /* User */]();
        user.id = data.userId;
        user.email = data.email;
        if (data.name.indexOf(" ") != -1) {
            user.nom = data.name.split(" ")[0];
            user.prenom = data.name.split(" ")[1];
        }
        else {
            user.nom = data.name;
            user.prenom = "--";
        }
        user.urlAvatar = data.image;
        this.restangular.one("user").post("login-facebook", user).subscribe(function (resp) {
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            if (resp.user.codecourt != null && resp.user.codecourt.length > 0) {
                var derivedKey = __WEBPACK_IMPORTED_MODULE_10_crypto_js___default.a.PBKDF2(resp.user.codecourt, "AlwaysTheSameSalt", {
                    keySize: 512 / 32,
                    iterations: 5
                }).toString();
                localStorage.setItem("codecourt", derivedKey);
            }
            _this.constante.user = resp.user;
            _this.constante.login(resp.user);
            _this.nav.setRoot(_this.main_page.component);
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    LoginPage.prototype.loggue_google = function (data) {
        var _this = this;
        var user = new __WEBPACK_IMPORTED_MODULE_8__cmy_model_cmy_model__["l" /* User */]();
        user.id = data.userId;
        user.email = data.email;
        if (data.name.indexOf(" ") != -1) {
            user.nom = data.name.split(" ")[0];
            user.prenom = data.name.split(" ")[1];
        }
        else {
            user.nom = data.name;
            user.prenom = "--";
        }
        user.urlAvatar = data.image;
        this.restangular.one("user").post("login-google", user).subscribe(function (resp) {
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            if (resp.user.codecourt != null && resp.user.codecourt.length > 0) {
                var derivedKey = __WEBPACK_IMPORTED_MODULE_10_crypto_js___default.a.PBKDF2(resp.user.codecourt, "AlwaysTheSameSalt", {
                    keySize: 512 / 32,
                    iterations: 5
                }).toString();
                localStorage.setItem("codecourt", derivedKey);
            }
            _this.constante.user = resp.user;
            _this.nav.setRoot(_this.main_page.component);
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    LoginPage.prototype.doGoogleLogin = function () {
        this.loading = this.loadingCtrl.create();
        // Here we will check if the user is already logged in because we don't want to ask users to log in each time they open the app
        var env = this;
        this.googleLoginService.trySilentLogin()
            .then(function (data) {
            // user is previously logged with Google and we have his data we will let him access the app
            env.loggue_google(data);
        }, function (error) {
            //we don't have the user data so we will ask him to log in
            env.googleLoginService.doGoogleLogin()
                .then(function (res) {
                env.loading.dismiss();
                env.loggue_google(res);
            }, function (err) {
                env.constante.traiteErreur(err, env);
            });
        });
    };
    LoginPage.prototype.goToSignup = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_3__signup_signup__["a" /* SignupPage */]);
    };
    LoginPage.prototype.goToForgotPassword = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__forgot_password_forgot_password__["a" /* ForgotPasswordPage */]);
    };
    LoginPage.prototype.showPrivacy = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_9__privacy_policy_privacy_policy__["a" /* PrivacyPolicyPage */]);
    };
    return LoginPage;
}());
LoginPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'login-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\login\login.html"*/'<ion-header class="login-header auth-header">\n  <ion-navbar>\n    <ion-title>Sign in</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="login-content auth-content">\n  <form class="login-form auth-form" [formGroup]="login" (ngSubmit)="doLogin()">\n    <ion-item>\n      <ion-input type="email" placeholder="Email, Téléphone ou login..." formControlName="email"></ion-input>\n    </ion-item>\n    <div *ngIf="login.hasError(\'required\') && login.touched" class="error-box">Email obligatoire</div>\n\n    <show-hide-container>\n      <ion-item>\n        <ion-input type="password" placeholder="Password" formControlName="password" show-hide-input></ion-input>\n      </ion-item>\n    </show-hide-container>\n    <button ion-button block class="auth-action-button login-button" type="submit" [disabled]="!login.valid">Log in</button>\n  </form>\n  <ion-row class="alt-options">\n    <ion-col no-padding width-50>\n      <button ion-button block clear class="forgot-button" (tap)="goToForgotPassword()">Forgot Password?</button>\n    </ion-col>\n    <ion-col no-padding width-50>\n      <button ion-button block clear class="signup-button" (tap)="goToSignup()">Sign up!</button>\n    </ion-col>\n  </ion-row>\n  <p class="auth-divider">\n    Or\n  </p>\n  <button ion-button block class="facebook-auth-button" (tap)="doFacebookLogin()">Log in with Facebook</button>\n  <button ion-button block class="google-auth-button" (tap)="doGoogleLogin()">Log in with Google</button>\n  <p class="auth-divider">\n    <button ion-button class="skip-button" (tap)="showPrivacy()" >Règles de confidentialité</button>\n  </p>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\login\login.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_7_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_5__facebook_login_service__["a" /* FacebookLoginService */],
        __WEBPACK_IMPORTED_MODULE_6__google_login_service__["a" /* GoogleLoginService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_7_ngx_restangular__["Restangular"],
        __WEBPACK_IMPORTED_MODULE_8__cmy_model_cmy_model__["a" /* Constante */]])
], LoginPage);

//# sourceMappingURL=login.js.map

/***/ }),

/***/ 174:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SignupPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(22);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_liste_event_cmy_liste_event__ = __webpack_require__(71);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__login_facebook_login_service__ = __webpack_require__(212);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__login_google_login_service__ = __webpack_require__(214);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__cmy_home_cmy_home__ = __webpack_require__(82);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__privacy_policy_privacy_policy__ = __webpack_require__(220);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};










var SignupPage = (function () {
    function SignupPage(nav, restangular, modal, toastCtrl, facebookLoginService, googleLoginService, loadingCtrl, constante) {
        this.nav = nav;
        this.restangular = restangular;
        this.modal = modal;
        this.toastCtrl = toastCtrl;
        this.facebookLoginService = facebookLoginService;
        this.googleLoginService = googleLoginService;
        this.loadingCtrl = loadingCtrl;
        this.constante = constante;
        this.main_page = { component: __WEBPACK_IMPORTED_MODULE_8__cmy_home_cmy_home__["a" /* Home */] };
        this.signup = new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* FormGroup */]({
            email: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].pattern("[a-z0-9.-_]+@[a-z.]+")])),
            phone: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(10), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].pattern("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$")])),
            nom: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5)])),
            prenom: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(3)])),
            password: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5)])),
            confirm_password: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5)])),
        });
    }
    SignupPage.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    SignupPage.prototype.doSignup = function () {
        var _this = this;
        // Enregistrement du nouveau user
        var user = new __WEBPACK_IMPORTED_MODULE_7__cmy_model_cmy_model__["l" /* User */]();
        user.email = this.signup.get('email').value;
        user.password = this.signup.get("password").value;
        user.phone = this.signup.get("phone").value;
        user.nom = this.signup.get('nom').value;
        user.prenom = this.signup.get('prenom').value;
        var passconf = this.signup.get("confirm_password").value;
        if (passconf != user.password) {
            this.presentToast("Les mots de passe sont différents!");
            this.signup.get("confirm_password").markAsDirty();
            return;
        }
        this.loading = this.loadingCtrl.create({
            content: 'Enregistrement...',
        });
        this.loading.present();
        this.restangular.one("user").post("create", user).subscribe(function (resp) {
            // Ajout à la liste
            _this.loading.dismissAll();
            localStorage.removeItem('id_token');
            localStorage.removeItem('user');
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            _this.constante.user = resp.user;
            // let component_page : any = { component: ListeEvent };
            _this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_3__cmy_liste_event_cmy_liste_event__["a" /* ListeEvent */]);
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    SignupPage.prototype.doFacebookSignup = function () {
        this.loading = this.loadingCtrl.create();
        // Here we will check if the user is already logged in
        // because we don't want to ask users to log in each time they open the app
        var env = this;
        this.facebookLoginService.getFacebookUser()
            .then(function (data) {
            // user is previously logged with FB and we have his data we will let him access the app
            // data : name, image,userID
            env.signup_social_fb(data);
        }, function (error) {
            //we don't have the user data so we will ask him to log in
            env.facebookLoginService.doFacebookLogin()
                .then(function (data) {
                env.loading.dismiss();
                env.signup_social_fb(data);
            }, function (err) {
                this.constante.traiteErreur(err, env);
            });
        });
    };
    SignupPage.prototype.signup_social_fb = function (data) {
        var _this = this;
        var user = new __WEBPACK_IMPORTED_MODULE_7__cmy_model_cmy_model__["l" /* User */]();
        user.id = data.userId;
        user.email = data.email;
        if (data.name.indexOf(" ") != -1) {
            user.nom = data.name.split(" ")[0];
            user.prenom = data.name.split(" ")[1];
        }
        else {
            user.nom = data.name;
            user.prenom = "--";
        }
        user.urlAvatar = data.image;
        // Connexion réel à l'application
        this.restangular.one("user").post("signup-facebook", user).subscribe(function (resp) {
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            _this.constante.login(resp.user);
            _this.nav.setRoot(_this.main_page.component);
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    SignupPage.prototype.signup_social_google = function (data) {
        var _this = this;
        var user = new __WEBPACK_IMPORTED_MODULE_7__cmy_model_cmy_model__["l" /* User */]();
        user.id = data.userId;
        user.email = data.email;
        if (data.name.indexOf(" ") != -1) {
            user.nom = data.name.split(" ")[0];
            user.prenom = data.name.split(" ")[1];
        }
        else {
            user.nom = data.name;
            user.prenom = "--";
        }
        user.urlAvatar = data.image;
        // Connexion réel à l'application
        this.restangular.one("user").post("signup-google", user).subscribe(function (resp) {
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            _this.constante.login(resp.user);
            _this.nav.setRoot(_this.main_page.component);
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    SignupPage.prototype.doGoogleSignup = function () {
        this.loading = this.loadingCtrl.create();
        // Here we will check if the user is already logged in because we don't want to ask users to log in each time they open the app
        var env = this;
        this.googleLoginService.trySilentLogin()
            .then(function (data) {
            // user is previously logged with Google and we have his data we will let him access the app
            env.signup_social_google(data);
        }, function (error) {
            //we don't have the user data so we will ask him to log in
            env.googleLoginService.doGoogleLogin()
                .then(function (res) {
                env.loading.dismiss();
                env.signup_social_google(res);
            }, function (err) {
                env.constante.traiteErreur(err, env);
                env.loading.dismiss();
            });
        });
    };
    SignupPage.prototype.showTermsModal = function () {
        var modal = this.modal.create(__WEBPACK_IMPORTED_MODULE_9__privacy_policy_privacy_policy__["a" /* PrivacyPolicyPage */]);
        modal.present();
    };
    SignupPage.prototype.showPrivacyModal = function () {
        var modal = this.modal.create(__WEBPACK_IMPORTED_MODULE_9__privacy_policy_privacy_policy__["a" /* PrivacyPolicyPage */]);
        modal.present();
    };
    return SignupPage;
}());
SignupPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'signup-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\signup\signup.html"*/'<ion-header class="signup-header auth-header">\n  <ion-navbar>\n    <ion-title>Sign up</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="signup-content auth-content">\n  <h2 class="auth-title">Create an account</h2>\n  <form class="signup-form auth-form" [formGroup]="signup" (ngSubmit)="doSignup()">\n    <ion-item>\n      <ion-input type="email" placeholder="Email" formControlName="email"></ion-input>\n    </ion-item>\n    <ion-item>\n      <ion-input type="phone" placeholder="Téléphone" formControlName="phone"></ion-input>\n    </ion-item>\n    <ion-row>\n      <ion-col col-6>\n        <ion-item>\n          <ion-input type="text" placeholder="Nom" formControlName="nom"></ion-input>\n        </ion-item>\n      </ion-col>\n      <ion-col  col-6>\n        <ion-item>\n          <ion-input type="text" placeholder="Prénom" formControlName="prenom"></ion-input>\n        </ion-item>\n      </ion-col>\n    </ion-row>\n    <show-hide-container>\n      <ion-item>\n        <ion-input type="password" placeholder="Password" formControlName="password" show-hide-input></ion-input>\n      </ion-item>\n    </show-hide-container>\n    <show-hide-container>\n      <ion-item>\n        <ion-input type="password" placeholder="Confirm password" formControlName="confirm_password" show-hide-input></ion-input>\n      </ion-item>\n    </show-hide-container>\n    <button ion-button block class="auth-action-button signup-button" type="submit" [disabled]="!signup.valid">Sign up</button>\n  </form>\n  <p class="auth-divider">\n    Or\n  </p>\n  <button ion-button block class="facebook-auth-button" (tap)="doFacebookSignup()">Sign up with Facebook</button>\n  <button ion-button block class="google-auth-button" (tap)="doGoogleSignup()">Sign up with Google</button>\n  <p class="legal-stuff">\n    By creating an account you agree to our <a class="legal-action" (tap)="showPrivacyModal()">Privacy policy</a> and <a class="legal-action" (tap)="showTermsModal()">Terms of use</a>\n  </p>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\signup\signup.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_4_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_4_ngx_restangular__["Restangular"],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */],
        __WEBPACK_IMPORTED_MODULE_5__login_facebook_login_service__["a" /* FacebookLoginService */],
        __WEBPACK_IMPORTED_MODULE_6__login_google_login_service__["a" /* GoogleLoginService */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_7__cmy_model_cmy_model__["a" /* Constante */]])
], SignupPage);

//# sourceMappingURL=signup.js.map

/***/ }),

/***/ 205:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ModalPhoto; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var ModalPhoto = (function () {
    function ModalPhoto(platform, params, viewCtrl, constante) {
        this.platform = platform;
        this.params = params;
        this.viewCtrl = viewCtrl;
        this.constante = constante;
        this.photos = new Array;
        for (var i = 0; i < 4; i++) {
            this.photos[i] = 'event/photoEvent' + (i + 1) + '.png';
        }
    }
    ModalPhoto.prototype.choose = function (photo) {
        var data = photo;
        this.viewCtrl.dismiss(data);
    };
    ModalPhoto.prototype.dismiss = function () {
        this.viewCtrl.dismiss();
    };
    return ModalPhoto;
}());
ModalPhoto = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-photo.html"*/'<ion-header>\n  <ion-toolbar>\n    <ion-title>\n      Choix photo\n    </ion-title>\n    <ion-buttons start>\n      <button ion-button (tap)="dismiss()">\n        <span ion-text color="primary" showWhen="ios">Cancel</span>\n        <ion-icon name="md-close" showWhen="android, windows"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n<ion-content class="list-mini-content">\n  <div class="list-mini">\n    <ion-list>\n      <button class="list-item"  (tap)="choose(photo)" ion-item *ngFor="let photo of photos">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(photo)\'></preload-image>\n          </ion-col>\n        </ion-row>\n      </button>\n    </ion-list>\n  </div>\n</ion-content>\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-photo.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["l" /* Platform */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ViewController */],
        __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */]])
], ModalPhoto);

//# sourceMappingURL=modal-photo.js.map

/***/ }),

/***/ 206:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeDocument; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_ajout_document_cmy_ajout_document__ = __webpack_require__(398);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_one_photo__ = __webpack_require__(400);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__ionic_native_file_opener__ = __webpack_require__(401);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







//import 'rxjs/Rx';
var ListeDocument = (function () {
    function ListeDocument(nav, constante, fileOpener, loadingCtrl, navParams, restangular, modalCtrl) {
        this.nav = nav;
        this.constante = constante;
        this.fileOpener = fileOpener;
        this.loadingCtrl = loadingCtrl;
        this.navParams = navParams;
        this.restangular = restangular;
        this.modalCtrl = modalCtrl;
        this.loading = this.loadingCtrl.create();
        this.operationAvecDepense = this.navParams.get("theOperation");
    }
    ;
    ListeDocument.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // This will query /accounts and return a observable.
        this.restangular.all('operation/' + this.operationAvecDepense.operation.id + '/document').getList().subscribe(function (histo) {
            console.log(histo);
            _this.documents = histo;
            _this.loading.dismissAll();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    ListeDocument.prototype.addNewDocument = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_ajout_document_cmy_ajout_document__["a" /* AjoutDocumentPage */], { theOperation: this.operationAvecDepense, theDocuments: this.documents });
    };
    ;
    ListeDocument.prototype.show = function (document) {
        if (document.type == 'Photo') {
            var modal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_one_photo__["a" /* ModalOnePhoto */], { thePhoto: document.url });
            modal.onDidDismiss(function (data) {
            });
            modal.present();
        }
        ;
        if (document.type == 'Doc') {
            if (document.url.endsWith("pdf")) {
                this.fileOpener.open(document.url, 'application/pdf').then(function (rep) {
                }, function (error) {
                });
            }
            else if (document.url.indexOf("_image%") >= 0) {
                var modal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_one_photo__["a" /* ModalOnePhoto */], { thePhoto: document.url });
                modal.onDidDismiss(function (data) {
                });
                modal.present();
            }
        }
        ;
    };
    ;
    return ListeDocument;
}());
ListeDocument = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'liste-document',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-document\cmy-liste-document.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Documents de l\'opération</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <ion-item-group>\n      <ion-item  (tap)="show(document)" class="notification-item" ion-item *ngFor="let document of documents">\n        <ion-avatar item-left>\n          <preload-image *ngIf="document.type==\'Photo\'" class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(document.url)\'></preload-image>\n          <preload-image *ngIf="document.type!=\'Photo\'" class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage("divers/Attach-icon.png")\'></preload-image>\n        </ion-avatar>\n        <h2 class="item-title">{{document.date}} - {{document.type}}</h2>\n        <p class="item-description" text-wrap>{{document.description}}</p>\n      </ion-item>\n  </ion-item-group>\n  <ion-fab right bottom>\n    <button (tap)="addNewDocument()" ion-fab color="danger"><ion-icon name="add"></ion-icon></button>\n  </ion-fab>\n</ion-content>\n\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-document\cmy-liste-document.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_6__ionic_native_file_opener__["a" /* FileOpener */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */]])
], ListeDocument);

//# sourceMappingURL=cmy-liste-document.js.map

/***/ }),

/***/ 212:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return FacebookLoginService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(58);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_facebook__ = __webpack_require__(407);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_native_storage__ = __webpack_require__(213);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var FacebookLoginService = (function () {
    function FacebookLoginService(http, nativeStorage, fb) {
        this.http = http;
        this.nativeStorage = nativeStorage;
        this.fb = fb;
        // FB_APP_ID: number = 826720427470540;
        this.FB_APP_ID = 712043742320271;
        this.fb.browserInit(this.FB_APP_ID, "v2.11");
    }
    FacebookLoginService.prototype.doFacebookLogin = function () {
        var _this = this;
        var env = this;
        return new Promise(function (resolve, reject) {
            //["public_profile"] is the array of permissions, you can add more if you need
            _this.fb.login(["public_profile"]).then(function (response) {
                //Getting name and gender properties
                var request = "/me?fields=name,gender,email";
                env.fb.api(request, [])
                    .then(function (user) {
                    //now we have the users info, let's save it in the NativeStorage
                    console.log(user);
                    var fb_user = {
                        userId: user.id,
                        email: user.email,
                        name: user.name,
                        gender: user.gender,
                        image: "https://graph.facebook.com/" + user.id + "/picture?type=large",
                        friends: null,
                        photos: null
                    };
                    env.nativeStorage.setItem('facebook_user', fb_user).then(function (rep) {
                        resolve(rep);
                    }, function (error) {
                        reject(error);
                    });
                });
            }, function (error) {
                reject(error);
            });
        });
    };
    FacebookLoginService.prototype.doFacebookLogout = function () {
        var _this = this;
        var env = this;
        return new Promise(function (resolve, reject) {
            _this.fb.logout()
                .then(function (res) {
                //user logged out so we will remove him from the NativeStorage
                env.nativeStorage.remove('facebook_user');
                resolve();
            }, function (error) {
                reject();
            });
        });
    };
    FacebookLoginService.prototype.getFacebookUser = function () {
        return this.nativeStorage.getItem('facebook_user');
    };
    FacebookLoginService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    return FacebookLoginService;
}());
FacebookLoginService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"],
        __WEBPACK_IMPORTED_MODULE_3__ionic_native_native_storage__["a" /* NativeStorage */],
        __WEBPACK_IMPORTED_MODULE_2__ionic_native_facebook__["a" /* Facebook */]])
], FacebookLoginService);

//# sourceMappingURL=facebook-login.service.js.map

/***/ }),

/***/ 214:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GoogleLoginService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__(58);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_google_plus__ = __webpack_require__(408);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_native_storage__ = __webpack_require__(213);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var GoogleLoginService = (function () {
    function GoogleLoginService(http, nativeStorage, googlePlus) {
        this.http = http;
        this.nativeStorage = nativeStorage;
        this.googlePlus = googlePlus;
        //webClientId: string = "1001905109734-cnkoa7unjev55lii0rftbfm0kvb37gqr.apps.googleusercontent.com";
        // webClientId: string = "272824026885-hv3ivie6j8iv86gt2aj1q2fb4cqf03po.apps.googleusercontent.com";
        // ou bien webClientId: string = "144202714235-r2f4grqrnl9obs93d26mv4a50hg9d9na.apps.googleusercontent.com"
        this.webClientId = "144202714235-fn6dv72gusevj4verqnmlve1jjh29mt8.apps.googleusercontent.com";
    }
    GoogleLoginService.prototype.trySilentLogin = function () {
        var _this = this;
        //checks if user is already signed in to the app and sign them in silently if they are.
        var env = this;
        return new Promise(function (resolve, reject) {
            env.googlePlus.trySilentLogin({
                'scopes': '',
                'webClientId': _this.webClientId,
                'offline': true
            })
                .then(function (user) {
                //now we have the users info, let's save it in the NativeStorage
                console.log(user);
                var google_user = {
                    userId: user.userId,
                    email: user.email,
                    name: user.displayName,
                    image: user.imageUrl,
                    friends: null,
                    photos: null
                };
                env.nativeStorage.setItem('google_user', google_user).then(function (rep) {
                    resolve(rep);
                }, function (error) {
                    reject(error);
                });
            }, function (error) {
                reject(error);
            });
        });
    };
    GoogleLoginService.prototype.doGoogleLogin = function () {
        var _this = this;
        var env = this;
        return new Promise(function (resolve, reject) {
            env.googlePlus.login({
                'scopes': '',
                'webClientId': _this.webClientId,
                'offline': true
            })
                .then(function (user) {
                console.log(user);
                var google_user = {
                    userId: user.userId,
                    email: user.email,
                    name: user.displayName,
                    image: user.imageUrl,
                    friends: null,
                    photos: null
                };
                env.nativeStorage.setItem('google_user', google_user).then(function (rep) {
                    resolve(rep);
                }, function (error) {
                    reject(error);
                });
            }, function (error) {
                reject(error);
            });
        });
    };
    GoogleLoginService.prototype.doGoogleLogout = function () {
        var _this = this;
        var env = this;
        return new Promise(function (resolve, reject) {
            _this.googlePlus.logout()
                .then(function (response) {
                //user logged out so we will remove him from the NativeStorage
                env.nativeStorage.remove('google_user');
                resolve();
            }, function (error) {
                reject(error);
            });
        });
    };
    GoogleLoginService.prototype.getGoogleUser = function () {
        return this.nativeStorage.getItem('google_user');
    };
    GoogleLoginService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    return GoogleLoginService;
}());
GoogleLoginService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_http__["Http"],
        __WEBPACK_IMPORTED_MODULE_3__ionic_native_native_storage__["a" /* NativeStorage */],
        __WEBPACK_IMPORTED_MODULE_2__ionic_native_google_plus__["a" /* GooglePlus */]])
], GoogleLoginService);

//# sourceMappingURL=google-login.service.js.map

/***/ }),

/***/ 215:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GestionAmi; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_invitation_ami_cmy_invitation_ami__ = __webpack_require__(409);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_choix_event__ = __webpack_require__(216);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__ = __webpack_require__(124);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__cmy_detail_ami_cmy_detail_ami__ = __webpack_require__(411);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








//import 'rxjs/Rx';
var GestionAmi = (function () {
    function GestionAmi(nav, constante, loadingCtrl, toastCtrl, modalController, restangular, params) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.toastCtrl = toastCtrl;
        this.modalController = modalController;
        this.restangular = restangular;
        this.params = params;
        this.visible = false;
        this.loading = this.loadingCtrl.create();
    }
    ;
    GestionAmi.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // récupération de toutes les relations
        this.restangular.all('user/' + this.constante.user.id + '/invitations').getList().subscribe(function (invits) {
            _this.invitations = invits;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
        this.restangular.all('user/' + this.constante.user.id + '/relationsAvecMontant').getList().subscribe(function (amis) {
            _this.amis = amis;
            _this.amisInitial = amis;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
        var sousmenus = new Array();
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Affecter", this.affecte, "share-alt"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Message", this.message, "send"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Detail", this.open, "open"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Quitter", this.closeMenu, "close"));
        this.menu.config(sousmenus);
    };
    ;
    GestionAmi.prototype.ajouteInvitation = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_invitation_ami_cmy_invitation_ami__["a" /* InvitationAmi */], { 'theInvitations': this.invitations });
    };
    ;
    GestionAmi.prototype.message = function (ami) {
        console.log("Message");
    };
    GestionAmi.prototype.open = function (ami) {
        this.closeMenu();
        this.nav.push(__WEBPACK_IMPORTED_MODULE_7__cmy_detail_ami_cmy_detail_ami__["a" /* DetailAmi */], { 'theAmi': ami });
    };
    ;
    GestionAmi.prototype.affecte = function (ami) {
        var _this = this;
        this.closeMenu();
        var modal = this.modalController.create(__WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_choix_event__["a" /* ModalChoixEvent */]);
        modal.onDidDismiss(function (event) {
            if (event == null) {
                return;
            }
            _this.loading = _this.loadingCtrl.create({
                content: 'Enregistrement...',
            });
            _this.loading.present();
            // Il est présnet mais n'était pas là avant
            var lien = _this.restangular.copy(new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["g" /* LienEventUser */](ami.id, event.id));
            lien.route = 'lienEventUser';
            lien.save().toPromise().then(function (rep) {
                _this.loading.dismiss();
            }, function (err) {
                _this.loading.dismiss();
                var toast = _this.toastCtrl.create({
                    message: "Le user est déjà affecté à cet event!",
                    duration: 3000,
                    position: 'top'
                });
                toast.present();
            });
        });
        modal.present();
    };
    ;
    GestionAmi.prototype.filtreAmi = function (ev) {
        console.log('Filtre');
        var val = ev.target.value;
        // if the value is an empty string don't filter the items
        if (val && val.trim() != '') {
            this.amis = this.amisInitial.filter(function (item) {
                return (JSON.stringify(item).toLocaleLowerCase().indexOf(val.toLowerCase()) > -1);
            });
        }
        else {
            this.amis = this.amisInitial;
        }
    };
    ;
    GestionAmi.prototype.showMenu = function (amiAvecDepense) {
        var ami = amiAvecDepense.user;
        this.visible = true;
        this.menu.show(this, ami, ami.urlAvatar);
        this.menu.toggle();
    };
    ;
    GestionAmi.prototype.closeMenu = function () {
        this.visible = false;
        this.menu.toggle();
        this.menu.close();
    };
    ;
    GestionAmi.prototype.blockEvent = function () {
        console.log("Il faut bloquer!!!");
        //    this.parent.cover.nativeElement.style.display="none";
        this.visible = false;
    };
    ;
    return GestionAmi;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('menu'),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["a" /* MenuCircular */])
], GestionAmi.prototype, "menu", void 0);
GestionAmi = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'gestion-ami',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-gestion-ami\cmy-gestion-ami.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Mes amis</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <div #cover id="cover" [ngClass]="{\'invisible\':!visible}" (tap)="blockEvent()" ion-fixed></div>\n  <menu-circular #menu class="circular-menu" [ngClass]="{\'invisible\':!visible}" ion-fixed></menu-circular>\n  <ion-searchbar (ionInput)="filtreAmi($event)"></ion-searchbar>\n  <ion-item-divider class="notifications-divider">Les invitations</ion-item-divider>\n  <div class="list-mini" *ngIf="invitations!=null && invitations.length>0">\n    <ion-list>\n      <button class="list-item"  ion-item *ngFor="let invitation of invitations">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image rounded-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(invitation.contact.photo)\'></preload-image>\n          </ion-col>\n          <ion-col no-padding col-6 class="item-content" >\n            <h3 class="item-title"> {{invitation.contact.displayName}}</h3>\n            <p class="item-description"> Envoyée le {{invitation.date}}</p>\n          </ion-col>\n          <ion-col no-padding col-3 justify-content-end  class="item-content">\n            <h3 class="item-title" text-wrap> {{invitation.etatReponse}}</h3>\n          </ion-col>\n        </ion-row>\n      </button>\n\n    </ion-list>\n  </div>\n  <div class="list-mini" *ngIf="invitations==null || invitations.length==0">\n    <ion-row align-items-center no-padding class="content-row one-line">\n      <h3 class="item-title"> Aucune invitation en cours</h3>\n    </ion-row>\n  </div>\n  <ion-item-divider class="notifications-divider">Les amis</ion-item-divider>\n  <div class="list-mini">\n    <ion-list>\n      <button class="list-item"  (tap)="showMenu(amiAvecDepense)"  ion-item *ngFor="let amiAvecDepense of amis">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image rounded-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(amiAvecDepense.user.urlAvatar)\'></preload-image>\n          </ion-col>\n          <ion-col  col-9 class="item-content" >\n            <h3 class="item-title"> {{amiAvecDepense.user.nom}} {{amiAvecDepense.user.prenom}}</h3>\n            <p  class="item-description" *ngIf="amiAvecDepense.aPaye>0"> Je lui dois {{amiAvecDepense.aPaye}} €</p>\n            <p  class="item-description"  *ngIf="amiAvecDepense.doit>0"> Il me doit {{amiAvecDepense.doit}} €</p>\n            <p  class="item-description"  *ngIf="amiAvecDepense.doit<0.1 && amiAvecDepense.aPaye<0.1"> On est quitte</p>\n          </ion-col>\n        </ion-row>\n      </button>\n\n    </ion-list>\n  </div>\n\n  <ion-fab right bottom>\n    <button (tap)="ajouteInvitation()" ion-fab color="danger"><ion-icon name="add"></ion-icon></button>\n  </ion-fab>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-gestion-ami\cmy-gestion-ami.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */]])
], GestionAmi);

//# sourceMappingURL=cmy-gestion-ami.js.map

/***/ }),

/***/ 216:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ModalChoixEvent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ModalChoixEvent = (function () {
    function ModalChoixEvent(loadingCtrl, constante, viewCtrl, restangular) {
        this.loadingCtrl = loadingCtrl;
        this.constante = constante;
        this.viewCtrl = viewCtrl;
        this.restangular = restangular;
        this.loading = this.loadingCtrl.create();
    }
    ModalChoixEvent.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        this.restangular.all('user/' + this.constante.user.id + '/events').getList().subscribe(function (events) {
            _this.events = events;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.loading.dismiss();
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ModalChoixEvent.prototype.choose = function (event) {
        // Sauvegarde du lien!!!
        this.viewCtrl.dismiss(event);
    };
    ModalChoixEvent.prototype.dismiss = function () {
        this.viewCtrl.dismiss();
    };
    return ModalChoixEvent;
}());
ModalChoixEvent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-choix-event.html"*/'<ion-header>\n  <ion-toolbar>\n    <ion-title>\n      Choix Event\n    </ion-title>\n    <ion-buttons start>\n      <button ion-button (tap)="dismiss()">\n        <span ion-text color="primary" showWhen="ios">Cancel</span>\n        <ion-icon name="md-close" showWhen="android, windows"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n<ion-content class="list-mini-content">\n  <div class="list-mini">\n    <ion-list>\n      <button class="list-item"  (press)="choose(event)"  ion-item *ngFor="let event of events">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(event.urlPhoto)\'></preload-image>\n          </ion-col>\n          <ion-col no-padding col-9 class="item-content">\n            <h3 class="item-title"> {{event.libelle}}</h3>\n            <p class="item-description"> Date : {{event.date}}</p>\n          </ion-col>\n        </ion-row>\n      </button>\n    </ion-list>\n  </div>\n</ion-content>\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-choix-event.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ViewController */],
        __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]])
], ModalChoixEvent);

//# sourceMappingURL=modal-choix-event.js.map

/***/ }),

/***/ 217:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeMessage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_detail_message_cmy_detail_message__ = __webpack_require__(412);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





//import 'rxjs/Rx';
var ListeMessage = (function () {
    function ListeMessage(constante, loadingCtrl, alertCtrl, nav, restangular) {
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.alertCtrl = alertCtrl;
        this.nav = nav;
        this.restangular = restangular;
        this.loading = this.loadingCtrl.create();
    }
    ListeMessage.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // This will query /accounts and return a observable.
        this.restangular.all('user/' + this.constante.user.id + '/messages').getList().subscribe(function (messages) {
            _this.tableauMessages = messages;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    ListeMessage.prototype.traite = function (message) {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_detail_message_cmy_detail_message__["a" /* DetailMessage */], { theMessage: message });
    };
    ;
    ListeMessage.prototype.trash = function (message) {
        var _this = this;
        var alert = this.alertCtrl.create({
            title: 'Suppression message',
            message: 'Etes-vous sur de voulier supprimer ce message?',
            buttons: [
                {
                    text: 'Annuler',
                    role: 'cancel',
                    handler: function () {
                        console.log('Cancel clicked');
                    }
                },
                {
                    text: 'Confirmer',
                    handler: function (data) {
                        _this.loading = _this.loadingCtrl.create();
                        _this.loading.present();
                        var mvtRest = _this.restangular.copy(message);
                        mvtRest.route = "message";
                        mvtRest.remove().toPromise().then(function (rep) {
                            _this.loading.dismissAll();
                            _this.constante.presentToast("Message supprimé!");
                            for (var _i = 0, _a = _this.tableauMessages; _i < _a.length; _i++) {
                                var tab = _a[_i];
                                var messages = tab.tableau;
                                var trouve = false;
                                for (var i = 0; i < messages.length; i++) {
                                    var msg = messages[i];
                                    if (msg.id == message.id) {
                                        messages.splice(i, 1);
                                        trouve = true;
                                        break;
                                    }
                                }
                                if (trouve)
                                    break;
                            }
                        }, function (errorResponse) {
                            _this.constante.traiteErreur(errorResponse, _this);
                        });
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    return ListeMessage;
}());
ListeMessage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'liste-message',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-list-message\cmy-liste-message.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Notifications</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="notifications-content">\n  <ion-item-group>\n    <div *ngFor="let tab of tableauMessages">\n      <ion-item-divider class="notifications-divider">{{tab.titre}}</ion-item-divider>\n        <ion-item [ngClass]="{\'row-opaque\' : message.dejaLu}" (tap)="traite(message)" (press)="trash(message)" class="notification-item" *ngFor="let message of tab.tableau">\n          <ion-avatar item-left>\n            <preload-image class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(message.emetteur.urlAvatar)\'></preload-image>\n          </ion-avatar>\n          <h2 class="item-title">{{message.emetteur.nom}} {{message.emetteur.prenom}}</h2>\n          <p class="item-description">{{message.titre}}<ion-icon name="warning"  style="color : red;" *ngIf="message.messageCache!=null && !message.actionRealise"></ion-icon></p>\n          <ion-note class="item-time" item-right>{{message.date}}</ion-note>\n        </ion-item>\n    </div>\n  </ion-item-group>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-list-message\cmy-liste-message.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]])
], ListeMessage);

//# sourceMappingURL=cmy-liste-message.js.map

/***/ }),

/***/ 218:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PaiementOrdre; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ionic_native_paypal__ = __webpack_require__(413);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





//import 'rxjs/Rx';
var PaiementOrdre = (function () {
    function PaiementOrdre(nav, constante, navParams, loadingCtrl, alertController, toastCtrl, restangular, payPal) {
        this.nav = nav;
        this.constante = constante;
        this.navParams = navParams;
        this.loadingCtrl = loadingCtrl;
        this.alertController = alertController;
        this.toastCtrl = toastCtrl;
        this.restangular = restangular;
        this.payPal = payPal;
        this.ordre = this.navParams.get("theOrdre");
        this.message = this.navParams.get("theMessage");
        this.loading = this.loadingCtrl.create();
    }
    PaiementOrdre.prototype.ionViewDidLoad = function () {
    };
    PaiementOrdre.prototype.paypal = function () {
        var _this = this;
        this.payPal.init({
            PayPalEnvironmentProduction: 'YOUR_PRODUCTION_CLIENT_ID',
            PayPalEnvironmentSandbox: 'AcVJLzisnjYYATxnMjyM3msePtk6TV-_za3cPUlJRgPaEM-fOQmgx7m_jHDC-ceYhzJOK1b8W3boq5a-'
        }).then(function (res) {
            _this.payPal.prepareToRender('PayPalEnvironmentSandbox', new __WEBPACK_IMPORTED_MODULE_4__ionic_native_paypal__["b" /* PayPalConfiguration */]({})).then(function () {
                var payment = new __WEBPACK_IMPORTED_MODULE_4__ionic_native_paypal__["c" /* PayPalPayment */]('' + _this.ordre.mouvement.montant, 'EUR', 'Description', 'sale');
                _this.payPal.renderSinglePaymentUI(payment).then(function (rep) {
                    // Successfully paid
                    console.log(rep);
                }, function (error) {
                    _this.constante.traiteErreur(error, _this);
                });
            }, function (error) {
                _this.constante.traiteErreur(error, _this);
            });
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    PaiementOrdre.prototype.visa = function () {
        this.presentToast("Le paiement VISA n'est pas encore disponible!");
    };
    PaiementOrdre.prototype.virement = function () {
        var _this = this;
        var alert = this.alertController.create({
            title: 'Valider le virement?',
            message: "Confirmez-vous que vous voulez régler par virement?",
            buttons: [
                {
                    text: 'Oui',
                    role: 'cancel',
                    handler: function () {
                        // On bascule juste le mouvement en "effectué"
                        _this.loading.present();
                        var aOrdre = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["k" /* Ordre */]();
                        _this.ordre.mouvement.etat = "Réalisé";
                        aOrdre.mouvement = _this.ordre.mouvement;
                        aOrdre.emetteur = _this.ordre.emetteur;
                        aOrdre.event = _this.ordre.event;
                        _this.restangular.one("mouvement").post("reglementParVirement", aOrdre).subscribe(function (rep) {
                            _this.loading.dismissAll();
                            _this.presentToast("Virement transmis");
                            _this.message.actionRealise = true;
                            var msgRest = _this.restangular.copy(_this.message);
                            msgRest.route = "message";
                            msgRest.save().toPromise().then(function (rep) {
                                console.log("Message action réalisée!");
                            }, function (error) {
                                _this.constante.traiteErreur(error, _this);
                            });
                        }, function (error) {
                            _this.constante.traiteErreur(error, _this);
                        });
                    }
                },
                {
                    text: 'Non',
                    handler: function () {
                        console.log("Abandon");
                    }
                }
            ]
        });
        alert.present();
    };
    PaiementOrdre.prototype.hand = function () {
        var _this = this;
        var alert = this.alertController.create({
            title: 'Valider le réglèment manuel?',
            message: "Confirmez-vous que vous avez régler en espèce ce paiement?",
            buttons: [
                {
                    text: 'Oui',
                    role: 'cancel',
                    handler: function () {
                        // On bascule juste le mouvement en "effectué"
                        _this.loading.present();
                        _this.ordre.mouvement.etat = "Réalisé";
                        var mvtRest = _this.restangular.copy(_this.ordre.mouvement);
                        mvtRest.route = "mouvement";
                        mvtRest.save().toPromise().then(function (rep) {
                            _this.loading.dismissAll();
                            _this.presentToast("Paiement enregistré");
                            _this.message.actionRealise = true;
                            var msgRest = _this.restangular.copy(_this.message);
                            msgRest.route = "message";
                            msgRest.save().toPromise().then(function (rep) {
                                console.log("Message action réalisée!");
                            }, function (error) {
                                _this.constante.traiteErreur(error, _this);
                            });
                        }, function (error) {
                            _this.constante.traiteErreur(error, _this);
                        });
                    }
                },
                {
                    text: 'Non',
                    handler: function () {
                        console.log("Abandon");
                    }
                }
            ]
        });
        alert.present();
    };
    PaiementOrdre.prototype.lydia = function () {
        this.presentToast("Le paiement Lydia n'est pas encore disponible!");
    };
    PaiementOrdre.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    return PaiementOrdre;
}());
PaiementOrdre = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'paiement-ordre',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-paiement-ordre\cmy-paiement-ordre.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle >\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Paiment {{ordre.mouvement.montant}}€ pour {{ordre.emetteur.prenom}}</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content>\n  <ion-grid>\n    <ion-row>\n      <H1>Event :</H1>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Titre :</ion-col>\n      <ion-col col-8>{{ordre.event.libelle}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Du :</ion-col>\n      <ion-col col-8>{{ordre.event.date}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Montant dépensé :</ion-col>\n      <ion-col col-8>{{ordre.event.montantDepense}}€</ion-col>\n    </ion-row>\n\n    <ion-item-divider></ion-item-divider>\n    <ion-row>\n      <H1>Paiement :</H1>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Pour :</ion-col>\n      <ion-col col-8>{{ordre.emetteur.nom}} {{ordre.emetteur.prenom}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Montant :</ion-col>\n      <ion-col col-8>{{ordre.mouvement.montant}}€</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Date :</ion-col>\n      <ion-col col-8>{{ordre.mouvement.date}}</ion-col>\n    </ion-row>\n    <ion-item-divider></ion-item-divider>\n    <ion-row>\n      <H1>Procéder au règlement :</H1>\n    </ion-row>\n    <ion-row *ngIf="!this.message.actionRealise">\n      <ion-col col-3 text-center>Paypal</ion-col>\n      <ion-col col-3 text-center>Virement</ion-col>\n      <ion-col col-3 text-center>Manuel</ion-col>\n      <ion-col col-3 text-center>Visa</ion-col>\n    </ion-row>\n    <ion-row style="height: 100px" *ngIf="!this.message.actionRealise">\n      <ion-img  (tap)="paypal()" src="./assets/images/payment/paypal.png" col-3></ion-img>\n      <ion-img  (tap)="virement()" src="./assets/images/payment/virement.png" col-3></ion-img>\n      <ion-img (tap)="hand()" src="./assets/images/payment/hand.png" col-3></ion-img>\n      <ion-img  (tap)="visa()" src="./assets/images/payment/visa.png" col-3></ion-img>\n    </ion-row>\n\n  </ion-grid>\n  <ion-badge *ngIf="this.message.actionRealise" color="secondary">\n    Payé!\n  </ion-badge>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-paiement-ordre\cmy-paiement-ordre.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_4__ionic_native_paypal__["a" /* PayPal */]])
], PaiementOrdre);

//# sourceMappingURL=cmy-paiement-ordre.js.map

/***/ }),

/***/ 219:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DetailOrdre; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




//import 'rxjs/Rx';
var DetailOrdre = (function () {
    function DetailOrdre(nav, constante, navParams, loadingCtrl) {
        this.nav = nav;
        this.constante = constante;
        this.navParams = navParams;
        this.loadingCtrl = loadingCtrl;
        this.ordre = this.navParams.get("theOrdre");
        this.loading = this.loadingCtrl.create();
    }
    DetailOrdre.prototype.ionViewDidLoad = function () {
    };
    return DetailOrdre;
}());
DetailOrdre = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'detail-ordre',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-ordre\cmy-detail-ordre.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle >\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Paiment {{ordre.mouvement.montant}}€ pour {{ordre.emetteur.prenom}}</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content>\n  <ion-grid>\n    <ion-row>\n      <H1>Event :</H1>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Titre :</ion-col>\n      <ion-col col-8>{{ordre.event.libelle}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Du :</ion-col>\n      <ion-col col-8>{{ordre.event.date}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Montant dépensé :</ion-col>\n      <ion-col col-8>{{ordre.event.montantDepense}}€</ion-col>\n    </ion-row>\n\n    <ion-item-divider></ion-item-divider>\n    <ion-row>\n      <H1>Paiement :</H1>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Pour :</ion-col>\n      <ion-col col-8>{{ordre.emetteur.nom}} {{ordre.emetteur.prenom}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Montant :</ion-col>\n      <ion-col col-8>{{ordre.mouvement.montant}}€</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Date :</ion-col>\n      <ion-col col-8>{{ordre.mouvement.date}}</ion-col>\n    </ion-row>\n\n  </ion-grid>\n  <ion-badge *ngIf="ordre.mouvement.etat==\'Réalisé\'" color="secondary">\n   Payé!\n  </ion-badge>\n  <ion-badge *ngIf="ordre.mouvement.etat==\'Transmis\'" color="danger">\n    En attente de règlement!\n  </ion-badge>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-ordre\cmy-detail-ordre.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */]])
], DetailOrdre);

//# sourceMappingURL=cmy-detail-ordre.js.map

/***/ }),

/***/ 220:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PrivacyPolicyPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var PrivacyPolicyPage = (function () {
    function PrivacyPolicyPage(view) {
        this.view = view;
    }
    PrivacyPolicyPage.prototype.dismiss = function () {
        this.view.dismiss();
    };
    return PrivacyPolicyPage;
}());
PrivacyPolicyPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'privacy-policy-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\privacy-policy\privacy-policy.html"*/'<ion-header class="privacy-header legal-header">\n  <ion-toolbar>\n    <ion-buttons start>\n      <button ion-button (click)="dismiss()">\n        <span showWhen="ios">Cancel</span>\n        <ion-icon name="md-close" showWhen="android,windows"></ion-icon>\n      </button>\n    </ion-buttons>\n    <ion-title>\n      Privacy Policy\n    </ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content class="privacy-content legal-content">\n\n  <h1>Politique modèle de confidentialité.</h1>\n  <p><strong>Introduction</strong><br />\n    Devant le développement des nouveaux outils de communication, il est nécessaire de porter une attention particulière à la protection de la vie privée.\n    C\'est pourquoi, nous nous engageons à respecter la confidentialité des renseignements personnels que nous collectons.\n  </p><hr/>\n  <h2>Collecte des renseignements personnels</h2>\n  <p>\n    Nous collectons les renseignements suivants :\n  </p>\n  <ul>\n    <li>Nom</li>\n    <li>Prénom</li>\n    <li>Adresse électronique</li>\n    <li>Numéro de téléphone / télécopieur</li>\n  </ul>\n\n  <p>\n    Les renseignements personnels que nous collectons sont recueillis au travers de formulaires et grâce à\n    l\'interactivité établie entre vous et notre site Web.\n    Nous utilisons également, comme indiqué dans la section suivante, des fichiers témoins et/ou journaux\n    pour réunir des informations vous concernant.\n  </p><hr/>\n  <h2>Formulaires&nbsp; et interactivité:</h2>\n  <p>\n    Vos renseignements personnels sont collectés par le biais de formulaire, à savoir :\n  </p>\n  <ul>\n    <li>Formulaire d\'inscription au site Web</li>\n  </ul>\n  <p>\n    Nous utilisons les renseignements ainsi collectés pour les finalités suivantes :\n  </p>\n  <ul>\n    <li>Contact</li>\n  </ul>\n  <p>\n    Vos renseignements sont également collectés par le biais de l\'interactivité pouvant s\'établir entre vous et notre site Web et ce, de la fa&ccedil;on suivante:\n  </p>\n  <ul>\n  </ul>\n  <p>\n    Nous utilisons les renseignements ainsi collectés pour les finalités suivantes :<br />\n  </p>\n  <ul>\n  </ul>\n  <hr/>\n  <h2>Droit d\'opposition et de retrait</h2>\n  <p>\n    Nous nous engageons à vous offrir un droit d\'opposition et de retrait quant à vos renseignements personnels.<br />\n    Le droit d\'opposition s\'entend comme étant la possiblité offerte aux internautes de refuser que leurs renseignements\n    personnels soient utilisées à certaines fins mentionnées lors de la collecte.<br />\n  </p>\n  <p>\n    Le droit de retrait s\'entend comme étant la possiblité offerte aux internautes de demander à ce que leurs\n    renseignements personnels ne figurent plus, par exemple, dans une liste de diffusion.<br />\n  </p>\n  <p>\n    Pour pouvoir exercer ces droits, vous pouvez : <br />\n    Code postal : 20 chemin de Saint-Paul, BRETX 31530<br />	Courriel : hcombey@gmail.com<br />	Téléphone : 0561852065<br />		</p><hr/>\n  <h2>Droit d\'accès</h2>\n  <p>\n    Nous nous engageons à reconnaître un droit d\'accès et de rectification aux personnes\n    concernées désireuses de consulter, modifier, voire radier les informations les concernant.<br />\n\n\n    L\'exercice de ce droit se fera :<br />\n    Code postal : 20 chemin de Saint-Paul, BRETX 31530<br />	Courriel : hcombey@gmail.com<br />	Téléphone : 0561852065<br />		</p><hr/>\n  <h2>Sécurité</h2>\n  <p>\n\n    Les renseignements personnels que nous collectons sont conservés\n    dans un environnement sécurisé. Les personnes travaillant pour nous sont tenues de respecter la confidentialité de vos informations.<br />\n    Pour assurer la sécurité de vos renseignements personnels, nous avons recours aux mesures suivantes :\n  </p>\n  <ul>\n    <li>Gestion des accès - personne autorisée</li>\n    <li>Gestion des accès - personne concernée</li>\n    <li>Logiciel de surveillance du réseau</li>\n    <li>Sauvegarde informatique</li>\n    <li>Identifiant / mot de passe</li>\n    <li>Pare-feu (Firewalls)</li>\n  </ul>\n\n  <p>\n    Nous nous engageons à maintenir un haut degré de confidentialité en intégrant les dernières innovations technologiques permettant d\'assurer\n    la confidentialité de vos transactions. Toutefois, comme aucun mécanisme n\'offre une sécurité maximale, une part de risque est toujours présente\n    lorsque l\'on utilise Internet pour transmettre des renseignements personnels.\n  </p><hr/>\n  <h2>Enfants</h2>\n  <p>\n    Notre site Web contient des sections destinées aux enfants.\n    La collecte de leurs renseignements personnels se fait avec\n    le consentement des parents ou du représentant de l\'enfant.\n    Nous demandons le consentement de ces derniers par le biais :\n  </p>\n  <ul>\n    <li>Formulaire d\'inscription\n    </li>\n  </ul>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\privacy-policy\privacy-policy.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ViewController */]])
], PrivacyPolicyPage);

//# sourceMappingURL=privacy-policy.js.map

/***/ }),

/***/ 231:
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = 231;

/***/ }),

/***/ 274:
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = 274;

/***/ }),

/***/ 319:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DetailEventPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_sms__ = __webpack_require__(72);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_ajout_participant_cmy_ajout_participant__ = __webpack_require__(320);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__cmy_creation_depense_cmy_creation_depense__ = __webpack_require__(395);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__cmy_modal_modal_choix_operation__ = __webpack_require__(396);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__cmy_liste_depense_cmy_liste_depense__ = __webpack_require__(397);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__cmy_bilan_event_cmy_bilan_event__ = __webpack_require__(402);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};










//import 'rxjs/Rx';
var DetailEventPage = (function () {
    function DetailEventPage(nav, loadingCtrl, toastCtrl, alertCtrl, constante, restangular, navParams, modalController, smsProvider) {
        this.nav = nav;
        this.loadingCtrl = loadingCtrl;
        this.toastCtrl = toastCtrl;
        this.alertCtrl = alertCtrl;
        this.constante = constante;
        this.restangular = restangular;
        this.navParams = navParams;
        this.modalController = modalController;
        this.smsProvider = smsProvider;
        this.loading = this.loadingCtrl.create();
        this.event = navParams.get("theEvent");
    }
    DetailEventPage.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // Lecture des participants de cet event
        this.restangular.all('event/' + this.event.id + '/users').getList().subscribe(function (particpants) {
            _this.participants = particpants;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    DetailEventPage.prototype.addNewParticipant = function () {
        if (this.event.etat != "Ouvert") {
            this.presentToast("L'event n'est plus ouvert... Impossible de modifier les participants!");
            return;
        }
        ;
        if (this.event.roles.indexOf("Createur") < 0) {
            this.constante.presentToast("Seul le créateur peut modifier les participants!");
            return;
        }
        ;
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_ajout_participant_cmy_ajout_participant__["a" /* AjoutParticipantPage */], { theEvent: this.event, participantsEvent: this.participants }).then(function (end) {
        });
    };
    DetailEventPage.prototype.rechercheOperation = function () {
        var _this = this;
        if (this.event.etat != "Ouvert") {
            this.presentToast("L'event n'est plus ouvert... Impossible d'ajouter des dépenses!");
            return;
        }
        var modal = this.modalController.create(__WEBPACK_IMPORTED_MODULE_7__cmy_modal_modal_choix_operation__["a" /* ModalChoixOperation */], { 'theEvent': this.event });
        modal.onDidDismiss(function (operationAvecDepense) {
            if (operationAvecDepense == null)
                return;
            _this.loading = _this.loadingCtrl.create({
                content: 'Enregistrement...',
            });
            _this.loading.present();
            var operation = operationAvecDepense.operation;
            var depense = new __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["c" /* Depense */](_this.constante.user.id, _this.event.id);
            depense.idOperation = operation.id;
            depense.montant = -operation.montant;
            depense.commentaire = operation.description;
            depense.date = operation.date;
            _this.restangular.one("depense").post("save", depense).subscribe(function (resp) {
                // Ajout à la liste
                _this.loading.dismissAll();
                console.log("dépense sauvée");
                depense.id = resp.id;
                var montant = depense.montant / _this.participants.length;
                for (var _i = 0, _a = _this.participants; _i < _a.length; _i++) {
                    var participant = _a[_i];
                    if (participant.user.id != depense.idPayeur)
                        participant.doit += montant;
                    else {
                        participant.aPaye += depense.montant;
                        participant.doit -= depense.montant - montant;
                    }
                }
                _this.event.montantTotal += depense.montant;
                _this.event.montantDepense += depense.montant;
                for (var _b = 0, _c = _this.participants; _b < _c.length; _b++) {
                    var participant = _c[_b];
                    if (participant.user.id == _this.constante.user.id)
                        _this.event.montantDu = participant.doit;
                }
                _this.constante.touchEvent(_this.event);
            }, function (errorResponse) {
                _this.constante.traiteErreur(errorResponse, _this);
            });
        });
        modal.present();
    };
    ;
    DetailEventPage.prototype.ajouteDepense = function () {
        if (this.event.etat != "Ouvert") {
            this.presentToast("L'event n'est plus ouvert... Impossible d'ajouter des dépenses!");
            return;
        }
        this.nav.push(__WEBPACK_IMPORTED_MODULE_6__cmy_creation_depense_cmy_creation_depense__["a" /* CreationDepensePage */], { theEvent: this.event, theParticipants: this.participants });
    };
    ;
    DetailEventPage.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    ;
    DetailEventPage.prototype.donneArgent = function (participant) {
        var _this = this;
        var alert = this.alertCtrl.create({
            title: 'Payer ' + participant.user.prenom,
            message: 'Quel montant avez-vous donner à ' + participant.user.prenom + '?',
            inputs: [
                {
                    name: 'montant',
                    placeholder: 'Montant en €'
                }
            ],
            buttons: [
                {
                    text: 'Annuler',
                    role: 'cancel',
                    handler: function () {
                        console.log('Cancel clicked');
                    }
                },
                {
                    text: 'Confirmer',
                    handler: function (data) {
                        _this.loading = _this.loadingCtrl.create();
                        _this.loading.present();
                        // On va créer un mouvement
                        var mvt = new __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["i" /* Mouvement */](_this.constante.user.id, _this.event.id);
                        mvt.commentaire = "Argent donné";
                        mvt.date = new Date();
                        mvt.idDestinataire = participant.user.id;
                        mvt.etat = "Réalisé";
                        mvt.montant = data.montant;
                        var mvtRest = _this.restangular.copy(mvt);
                        mvtRest.route = "mouvement";
                        mvtRest.save().toPromise().then(function (rep) {
                            _this.loading.dismissAll();
                            _this.presentToast("Argent envoyé!");
                            // Recalcul des montatns par particpant
                            for (var _i = 0, _a = _this.participants; _i < _a.length; _i++) {
                                var participant_1 = _a[_i];
                                if (participant_1.user.id == rep.idDestinataire) {
                                    participant_1.doit += rep.montant;
                                    participant_1.aPaye -= rep.montant;
                                }
                                if (participant_1.user.id == rep.idEmetteur) {
                                    participant_1.doit -= rep.montant;
                                    participant_1.aPaye += rep.montant;
                                }
                            }
                        }, function (errorResponse) {
                            _this.constante.traiteErreur(errorResponse, _this);
                        });
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    DetailEventPage.prototype.sms = function (participant) {
        var _this = this;
        console.log("SMS!");
        var options = {
            replaceLineBreaks: false,
            android: {
                intent: 'INTENT'
            }
        };
        this.smsProvider.send("0682667921", 'Ca roule?', options).then(function (rep) {
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    DetailEventPage.prototype.interaction = function (participant) {
        console.log("interaction!");
    };
    ;
    DetailEventPage.prototype.phone = function (participant) {
        var _this = this;
        console.log("SMS!");
        var options = {
            replaceLineBreaks: false,
            android: {
                intent: ''
            }
        };
        this.smsProvider.send("0682667921", 'Ca roulegggg?', options).then(function (rep) {
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    DetailEventPage.prototype.showDepense = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_8__cmy_liste_depense_cmy_liste_depense__["a" /* ListeDepense */], { theEvent: this.event, theParticipants: this.participants });
    };
    ;
    DetailEventPage.prototype.bilan = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_9__cmy_bilan_event_cmy_bilan_event__["a" /* BilanEvent */], { theEvent: this.event, theParticipants: this.participants });
    };
    ;
    DetailEventPage.prototype.toggleRole = function (participant) {
        var _this = this;
        if (this.event.roles.indexOf("Createur") < 0) {
            this.constante.presentToast("Seul le créateur peut modifier les participants!");
            return;
        }
        ;
        // Bascule du role createur
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        this.restangular.one("event/" + this.event.id + "/" + participant.user.id + "/toggleRole").get().subscribe(function (lien) {
            // console.log(lien);
            participant.roles = lien.roles;
            _this.loading.dismissAll();
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    return DetailEventPage;
}());
DetailEventPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'detail-event-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-event\cmy-detail-event.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle >\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title> {{ event.libelle}} du {{ event.date}}</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <div class="list-mini">\n    <ion-list>\n      <div class="list-item"  ion-item  *ngFor="let participant of participants">\n        <ion-row align-items-center no-padding class="content-row one-line" (press)="donneArgent(participant)">\n        <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n        <ion-col no-padding col-3 class="item-avatar">\n          <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(participant.user.urlAvatar)\'></preload-image>\n        </ion-col>\n        <ion-col no-padding col-8 class="item-content">\n          <h3 class="item-title"> {{participant.user.nom}} {{participant.user.prenom}}</h3>\n          <p class="item-description"  *ngIf="participant.aPaye.toFixed(2)>0"> A payé {{participant.aPaye.toFixed(2)}} €</p>\n          <p class="item-description"  *ngIf="participant.doit.toFixed(2)>0"> Doit payer {{participant.doit.toFixed(2)}} €</p>\n          <p class="item-description" *ngIf="participant.doit.toFixed(2)<0"> Doit récupérer {{-participant.doit.toFixed(2)}} €</p>\n\n        </ion-col>\n          <ion-col no-padding  col-1 style="text-align: end;"  >\n\n              <ion-icon *ngIf="participant.roles.indexOf(\'Createur\')>=0"  name="star" (tap)="toggleRole(participant)"></ion-icon>\n              <ion-icon *ngIf="participant.roles.indexOf(\'Createur\')<0" name="star-outline" (tap)="toggleRole(participant)"></ion-icon>\n\n          </ion-col>\n      </ion-row>\n    </div>\n  </ion-list>\n  </div>\n  <ion-row style="height: 150px"></ion-row>\n  <ion-fab right bottom>\n    <button (tap)="addNewParticipant()" ion-fab color="danger"><ion-icon name="person-add"></ion-icon></button>\n  </ion-fab>\n</ion-content>\n\n\n<ion-footer>\n  <ion-segment color="primary">\n    <ion-segment-button (tap)="ajouteDepense()" value="cash">\n      <ion-icon name="cash"></ion-icon>\n    </ion-segment-button>\n    <ion-segment-button value="search" (tap)="rechercheOperation()">\n      <ion-icon name="search"></ion-icon>\n    </ion-segment-button>\n    <ion-segment-button value="bookmark" (tap)="showDepense()">\n      <ion-icon name="pricetags"></ion-icon>\n    </ion-segment-button>\n    <ion-segment-button value="bookmark" (tap)="bilan()">\n      <ion-icon name="podium"></ion-icon>\n    </ion-segment-button>\n  </ion-segment>\n</ion-footer>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-event\cmy-detail-event.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */], __WEBPACK_IMPORTED_MODULE_2__ionic_native_sms__["a" /* SMS */]])
], DetailEventPage);

//# sourceMappingURL=cmy-detail-event.js.map

/***/ }),

/***/ 320:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AjoutParticipantPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AjoutParticipantPage = (function () {
    function AjoutParticipantPage(nav, constante, loadingCtrl, restangular, params) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.params = params;
        this.encours = false;
        this.loading = this.loadingCtrl.create();
        this.event = params.get("theEvent");
        this.participantsEvent = params.get("participantsEvent");
    }
    AjoutParticipantPage.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // récupération de toutes les relations
        this.restangular.all('user/' + this.constante.user.id + '/relations').getList().subscribe(function (relations) {
            _this.relations = new Array();
            for (var _i = 0, relations_1 = relations; _i < relations_1.length; _i++) {
                var relation = relations_1[_i];
                // Est-ce qu'il est déjà ajouté
                var trouve = false;
                for (var _a = 0, _b = _this.participantsEvent; _a < _b.length; _a++) {
                    var userAvecDepense = _b[_a];
                    if (userAvecDepense.user.id == relation.id)
                        trouve = true;
                }
                var relationPresent = new ParticipantPresent(relation, trouve);
                _this.relations.push(relationPresent);
            }
            _this.relationsSave = new Array();
            for (var _c = 0, _d = _this.relations; _c < _d.length; _c++) {
                var relation = _d[_c];
                _this.relationsSave.push(relation.present);
            }
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    AjoutParticipantPage.prototype.close = function () {
        var _this = this;
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        this.encours = true;
        var tab = [];
        var commandes = new Array();
        for (var _i = 0, _a = this.relations; _i < _a.length; _i++) {
            var participantPresent = _a[_i];
            // Est-ce qu'il était là avant?
            var present = false;
            for (var _b = 0, _c = this.participantsEvent; _b < _c.length; _b++) {
                var userAvecDepense = _c[_b];
                if (userAvecDepense.user.id == participantPresent.participant.id) {
                    present = true;
                    break;
                }
            }
            var commande = new CommandeAddDelParticipant();
            commande.lienEventUser = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["g" /* LienEventUser */](participantPresent.participant.id, this.event.id);
            if (participantPresent.present && !present) {
                // Il est présnet mais n'était pas là avant
                //let lien = this.restangular.copy(new LienEventUser(participantPresent.participant.id,this.event.id));
                //lien.route='lienEventUser';
                //tab.push(lien.save().toPromise());
                commande.commande = "ADD";
            }
            else if (!participantPresent.present && present) {
                // Il a été enlevé
                //let lien = new LienEventUser(participantPresent.participant.id,this.event.id);
                //tab.push(this.restangular.one("event").post("supprimeUser",lien).toPromise());
                commande.commande = "DEL";
            }
            if (commande.commande != null)
                commandes.push(commande);
        }
        if (commandes.length > 0) {
            // dans les 2 cas, je refresh la liste pour ne pas refaire les répartitions en local
            this.restangular.one("event").post("ajoutSuppressionUser", commandes).subscribe(function (rep) {
                //this.refresh();
                _this.participantsEvent.splice(0, _this.participantsEvent.length);
                for (var _i = 0, rep_1 = rep; _i < rep_1.length; _i++) {
                    var part = rep_1[_i];
                    _this.participantsEvent.push(part);
                }
                _this.loading.dismiss();
                _this.encours = false;
                _this.nav.pop();
            }, function (err) {
                _this.encours = false;
                var cpt = 0;
                for (var _i = 0, _a = _this.relationsSave; _i < _a.length; _i++) {
                    var present = _a[_i];
                    _this.relations[cpt].present = present;
                    cpt++;
                }
                _this.constante.traiteErreur(err, _this);
            });
        }
        else {
            this.encours = false;
            this.nav.pop();
        }
    };
    ;
    AjoutParticipantPage.prototype.toogle = function (relation) {
        relation.present = !relation.present;
    };
    ;
    return AjoutParticipantPage;
}());
AjoutParticipantPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'ajout-participant-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-ajout-participant\cmy-ajout-participant.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Participants de {{event.libelle}}</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content>\n  <ion-grid>\n    <ion-row  (tap)="relation.present=!relation.present" *ngFor="let relation of relations">\n      <ion-col no-padding col-3 class="item-avatar"  [ngClass]="{\'row-opaque\' : !relation.present}">\n        <preload-image class="avatar-image rounded-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(relation.participant.urlAvatar)\'></preload-image>\n      </ion-col>\n      <ion-col no-padding col-7 class="item-content"  [ngClass]="{\'row-opaque\' : !relation.present}">\n        <h3 class="item-title"> {{relation.participant.nom}} {{ relation.participant.prenom}}</h3>\n        <p class="item-description"> {{relation.participant.email}}</p>\n      </ion-col>\n      <ion-col no-padding col-2 justify-content-end>\n        <ion-icon *ngIf="relation.present" name="star"></ion-icon>\n      </ion-col>\n    </ion-row>\n  </ion-grid>\n  <ion-fab right bottom>\n    <button (tap)="close()" [disabled]=\'encours\' ion-fab color="danger"><ion-icon name="done-all"></ion-icon></button>\n  </ion-fab>\n</ion-content>\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-ajout-participant\cmy-ajout-participant.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */]])
], AjoutParticipantPage);

;
var ParticipantPresent = (function () {
    function ParticipantPresent(user, pres) {
        this.user = user;
        this.pres = pres;
        this.participant = user;
        this.present = pres;
    }
    return ParticipantPresent;
}());
var CommandeAddDelParticipant = (function () {
    function CommandeAddDelParticipant() {
    }
    return CommandeAddDelParticipant;
}());
//# sourceMappingURL=cmy-ajout-participant.js.map

/***/ }),

/***/ 395:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CreationDepensePage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(22);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ionic_native_camera__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_photo__ = __webpack_require__(205);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

//import 'rxjs/Rx';






var CreationDepensePage = (function () {
    function CreationDepensePage(nav, loadingCtrl, modalCtrl, constante, restangular, camera, params, toastCtrl) {
        this.nav = nav;
        this.loadingCtrl = loadingCtrl;
        this.modalCtrl = modalCtrl;
        this.constante = constante;
        this.restangular = restangular;
        this.camera = camera;
        this.params = params;
        this.toastCtrl = toastCtrl;
        this.encours = false;
        this.imageUrl = null;
        this.imageDataCamera = null;
        this.valid = false;
        this.options = {
            quality: 80,
            sourceType: this.camera.PictureSourceType.CAMERA,
            destinationType: this.camera.DestinationType.DATA_URL,
            encodingType: this.camera.EncodingType.PNG,
            saveToPhotoAlbum: false,
            targetWidth: 200,
            targetHeight: 200
        };
        this.creationMouvementForm = new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* FormGroup */]({
            commentaire: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required
            ])),
            montant: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required
            ])),
            date: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](new Date().toISOString(), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required
            ]))
        });
        this.event = params.get("theEvent");
        this.participants = params.get("theParticipants");
        var theUser = this.constante.user; //JSON.parse(localStorage.getItem('user'));
        this.depense = new __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["c" /* Depense */](theUser.id, this.event.id);
    }
    ;
    CreationDepensePage.prototype.popupPayePar = function () {
        this.presentToast("Pas encore implementé");
    };
    ;
    CreationDepensePage.prototype.popupPartage = function () {
        this.presentToast("Pas encore implementé");
    };
    ;
    CreationDepensePage.prototype.saveMouvement = function () {
        var _this = this;
        this.encours = true;
        this.depense.commentaire = this.creationMouvementForm.get('commentaire').value;
        this.depense.date = this.creationMouvementForm.get('date').value;
        this.depense.montant = parseFloat(this.creationMouvementForm.get('montant').value);
        this.depense.typeRepartition = "equitable";
        if (this.imageDataCamera == null) {
        }
        else {
            // Nouvelle photo en envoyer
            this.imageUrl = this.createFileName();
            this.imageUrl += "==" + this.imageDataCamera;
        }
        ;
        this.depense.urlPhoto = this.imageUrl;
        this.loading = this.loadingCtrl.create({
            content: 'Enregistrement...',
        });
        this.loading.present();
        this.restangular.one("depense").post("save", this.depense).subscribe(function (resp) {
            var montant = _this.depense.montant / _this.participants.length;
            for (var _i = 0, _a = _this.participants; _i < _a.length; _i++) {
                var participant = _a[_i];
                if (participant.user.id != _this.depense.idPayeur)
                    participant.doit += montant;
                else {
                    participant.aPaye += _this.depense.montant;
                    participant.doit -= _this.depense.montant - montant;
                }
            }
            _this.event.montantTotal += _this.depense.montant;
            _this.event.montantDepense += _this.depense.montant;
            for (var _b = 0, _c = _this.participants; _b < _c.length; _b++) {
                var participant = _c[_b];
                if (participant.user.id == _this.constante.user.id)
                    _this.event.montantDu = participant.doit;
            }
            _this.constante.touchEvent(_this.event);
            _this.loading.dismissAll();
            _this.nav.pop();
        }, function (errorResponse) {
            _this.encours = false;
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    CreationDepensePage.prototype.choosePhoto = function () {
        var _this = this;
        var modal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_photo__["a" /* ModalPhoto */]);
        modal.onDidDismiss(function (data) {
            console.log(data);
            _this.imageUrl = data;
        });
        modal.present();
    };
    ;
    CreationDepensePage.prototype.takePhoto = function () {
        var _this = this;
        this.options.sourceType = this.camera.PictureSourceType.CAMERA;
        this.camera.getPicture(this.options).then(function (imageData) {
            _this.imageDataCamera = "data:image/png;base64," + imageData;
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    CreationDepensePage.prototype.chooseGallery = function () {
        var _this = this;
        this.options.sourceType = this.camera.PictureSourceType.PHOTOLIBRARY;
        this.camera.getPicture(this.options).then(function (imageData) {
            _this.imageDataCamera = "data:image/png;base64," + imageData;
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    // Create a new name for the image
    CreationDepensePage.prototype.createFileName = function () {
        var d = new Date();
        var n = d.getTime();
        var newFileName = "mouvement/" + this.constante.user.id + "_" + n + ".png";
        return newFileName;
    };
    ;
    CreationDepensePage.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    ;
    return CreationDepensePage;
}());
CreationDepensePage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'creation-depense-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-creation-depense\cmy-creation-depense.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Nouvelle dépense</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="forms-examples-content">\n  <div  class="forms-wrapper">\n    <div  class="post-example-view">\n     <form class="sample-form post-form" [formGroup]="creationMouvementForm" (ngSubmit)="saveMouvement()">\n    <section class="form-section">\n      <ion-item>\n        <ion-input type="text" placeholder="Description" formControlName="commentaire"></ion-input>\n      </ion-item>\n      <ion-item>\n        <ion-input type="number" placeholder="Montant" formControlName="montant"></ion-input>\n      </ion-item>\n      <ion-item>\n        Payé par\n        <button ion-button  type="button" outline item-end (tap)="popupPayePar()">Vous</button>\n      </ion-item>\n      <ion-item>\n        Partagé\n        <button ion-button  type="button" outline item-end (tap)="popupPartage()">Equitablement</button>\n      </ion-item>\n\n      <ion-item class="multi-input time-item">\n        <ion-label floating>Date</ion-label>\n        <ion-datetime formControlName="date" displayFormat="DD/MM/YYYY" pickerFormat="DD-MM-YYYY"></ion-datetime>\n      </ion-item>\n      <button ion-button block large  type="button"  class="upload-image-button" >\n        <ion-icon name="camera" (tap)="takePhoto()"></ion-icon><ion-icon name="folder" (tap)="choosePhoto()"></ion-icon><ion-icon name="images" (tap)="chooseGallery()"></ion-icon>\n        <img [src]="imageDataCamera" *ngIf="imageDataCamera" />\n        <img [src]="constante.getUrlImage(imageUrl)" *ngIf="imageUrl" />\n        <h3 class="button-title">Prenez une photo</h3>\n      </button>\n    </section>\n\n    <section class="form-section">\n      <button ion-button block class="form-action-button create-post-button" type="submit" [disabled]="!creationMouvementForm.valid">Enregistrer!</button>\n    </section>\n  </form>\n    </div>\n  </div>\n\n</ion-content>\n\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-creation-depense\cmy-creation-depense.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_6_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_6_ngx_restangular__["Restangular"],
        __WEBPACK_IMPORTED_MODULE_4__ionic_native_camera__["a" /* Camera */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */]])
], CreationDepensePage);

;
//# sourceMappingURL=cmy-creation-depense.js.map

/***/ }),

/***/ 396:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ModalChoixOperation; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ModalChoixOperation = (function () {
    function ModalChoixOperation(loadingCtrl, constante, viewCtrl, restangular, navParam, toastCtrl) {
        this.loadingCtrl = loadingCtrl;
        this.constante = constante;
        this.viewCtrl = viewCtrl;
        this.restangular = restangular;
        this.navParam = navParam;
        this.toastCtrl = toastCtrl;
        this.loading = this.loadingCtrl.create();
        this.event = this.navParam.get("theEvent");
    }
    ModalChoixOperation.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        this.restangular.all('user/' + this.constante.user.id + '/operations').getList().subscribe(function (operations) {
            _this.tableauOperations = operations;
            _this.tableauOperationsInitial = operations;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.loading.dismiss();
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    ModalChoixOperation.prototype.choose = function (operationAvecDepense) {
        // Sauvegarde du lien!!!
        if (operationAvecDepense.operation.montant > 0) {
            var toast = this.toastCtrl.create({
                message: "Merci de sélectionner un débit uniquement!",
                duration: 3000,
                position: 'top'
            });
            toast.present();
            return;
        }
        if (operationAvecDepense.depense != null) {
            var toast = this.toastCtrl.create({
                message: "Opération déjà utilisée!",
                duration: 3000,
                position: 'top'
            });
            toast.present();
            return;
        }
        this.viewCtrl.dismiss(operationAvecDepense);
    };
    ;
    ModalChoixOperation.prototype.dismiss = function () {
        this.viewCtrl.dismiss();
    };
    ;
    ModalChoixOperation.prototype.filtreOperation = function (ev) {
        console.log('Filtre');
        var val = ev.target.value;
        // if the value is an empty string don't filter the items
        if (val && val.trim() != '') {
            this.tableauOperations = this.tableauOperationsInitial.filter(function (item) {
                return (JSON.stringify(item).toLocaleLowerCase().indexOf(val.toLowerCase()) > -1);
            });
        }
        else {
            this.tableauOperations = this.tableauOperationsInitial;
        }
    };
    ;
    return ModalChoixOperation;
}());
ModalChoixOperation = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'modal-choix-operation',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-choix-operation.html"*/'<ion-header>\n  <ion-toolbar>\n    <ion-title>\n      Choix opération\n    </ion-title>\n    <ion-buttons start>\n      <button ion-button (tap)="dismiss()">\n        <span ion-text color="primary" showWhen="ios">Cancel</span>\n        <ion-icon name="md-close" showWhen="android, windows"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n<ion-content class="list-mini-content">\n  <ion-searchbar (ionInput)="filtreOperation($event)"></ion-searchbar>\n  <ion-item-group>\n    <div *ngFor="let tab of tableauOperations">\n      <ion-item-divider class="notifications-divider">{{tab.titre}}</ion-item-divider>\n      <ion-item [ngClass]="{ \'AvecDepense\':operationAvecDepense.depense!=null}"  (press)="choose(operationAvecDepense)" ion-item *ngFor="let operationAvecDepense of tab.tableau">\n        <div style="display:flex">\n          <div style="flex:1" text-wrap>{{operationAvecDepense.operation.date.substring(0,5)}} {{operationAvecDepense.operation.description}}</div>\n          <div style="width: 30px;margin-right: 10px;" *ngIf="operationAvecDepense.operation.urlPhotoEmetteur"><preload-image style="border-radius: 50%" class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(operationAvecDepense.operation.urlPhotoEmetteur)\'></preload-image></div>\n          <div [ngClass]="{\'negatif\':operationAvecDepense.operation.montant<0,\'positif\':operationAvecDepense.operation.montant>=0}">{{operationAvecDepense.operation.montant.toFixed(2)}} €</div>\n        </div>\n      </ion-item>\n    </div>\n  </ion-item-group>\n</ion-content>\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-choix-operation.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ViewController */],
        __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */]])
], ModalChoixOperation);

;
//# sourceMappingURL=modal-choix-operation.js.map

/***/ }),

/***/ 397:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeDepense; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_liste_document_cmy_liste_document__ = __webpack_require__(206);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





//import 'rxjs/Rx';
var ListeDepense = (function () {
    function ListeDepense(nav, constante, loadingCtrl, restangular, alertController, toastCtrl, modalController, navParam) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.alertController = alertController;
        this.toastCtrl = toastCtrl;
        this.modalController = modalController;
        this.navParam = navParam;
        this.loading = this.loadingCtrl.create();
        this.event = navParam.get("theEvent");
        this.participants = navParam.get("theParticipants");
    }
    ListeDepense.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        this.restangular.all('event/' + this.event.id + '/depenses').getList().subscribe(function (depenses) {
            _this.depenses = new Array();
            for (var _i = 0, depenses_1 = depenses; _i < depenses_1.length; _i++) {
                var depense = depenses_1[_i];
                var dep = new DepenseAvecUser();
                dep.depense = depense;
                for (var _a = 0, _b = _this.participants; _a < _b.length; _a++) {
                    var particpant = _b[_a];
                    if (particpant.user.id == depense.idPayeur) {
                        dep.user = particpant.user;
                        break;
                    }
                }
                _this.depenses.push(dep);
            }
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.loading.dismiss();
            _this.constante.traiteErreur(errorResponse, _this);
        });
        this.restangular.all('event/' + this.event.id + '/mouvements').getList().subscribe(function (mouvements) {
            _this.paiements = new Array();
            for (var _i = 0, mouvements_1 = mouvements; _i < mouvements_1.length; _i++) {
                var mouvement = mouvements_1[_i];
                var mvt = new MouvementAvecUser();
                mvt.mouvement = mouvement;
                _this.paiements.push(mvt);
            }
            for (var _a = 0, _b = _this.paiements; _a < _b.length; _a++) {
                var mouvement = _b[_a];
                for (var _c = 0, _d = _this.participants; _c < _d.length; _c++) {
                    var particpant = _d[_c];
                    if (particpant.user.id == mouvement.mouvement.idEmetteur) {
                        mouvement.emetteur = particpant.user;
                        break;
                    }
                }
            }
            for (var _e = 0, _f = _this.paiements; _e < _f.length; _e++) {
                var mouvement = _f[_e];
                for (var _g = 0, _h = _this.participants; _g < _h.length; _g++) {
                    var particpant = _h[_g];
                    if (particpant.user.id == mouvement.mouvement.idDestinataire) {
                        mouvement.destinataire = particpant.user;
                        break;
                    }
                }
            }
        }, function (errorResponse) {
            _this.loading.dismiss();
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    ListeDepense.prototype.detailDepense = function (operation) {
        var _this = this;
        var alert = this.alertController.create({
            title: 'Modifier cette dépense',
            message: "Voulez-vous :",
            buttons: [
                {
                    text: 'Supprimer cette dépense',
                    role: 'cancel',
                    handler: function () {
                        if (operation.depense.idPayeur != _this.constante.user.id) {
                            var toast = _this.toastCtrl.create({
                                message: 'Vous ne pouvez pas modifier cette dépense',
                                duration: 3000,
                                position: 'top'
                            });
                            toast.present();
                            return;
                        }
                        _this.loading = _this.loadingCtrl.create({
                            content: 'Suppression...',
                        });
                        _this.loading.present();
                        // this.operationAvecDepense.depense.remove();
                        var newDepense = _this.restangular.copy(operation.depense);
                        newDepense.route = 'depense';
                        newDepense.remove().toPromise().then(function (resp) {
                            _this.loading.dismissAll();
                            console.log("dépense supprimée");
                            _this.event.montantTotal -= newDepense.montant;
                            _this.event.montantDepense -= newDepense.montant;
                            _this.constante.touchEvent(_this.event);
                            // suppression de la ligne
                            for (var i = 0; i < _this.depenses.length; i++) {
                                var dep = _this.depenses[i];
                                if (dep.depense.id == operation.depense.id) {
                                    _this.depenses.splice(i, 1);
                                    break;
                                }
                            }
                            // Mise à jour des montant participant
                            var montant = newDepense.montant / _this.participants.length;
                            for (var _i = 0, _a = _this.participants; _i < _a.length; _i++) {
                                var participant = _a[_i];
                                if (participant.user.id != newDepense.idPayeur)
                                    participant.doit -= montant;
                                else {
                                    participant.aPaye -= newDepense.montant;
                                    participant.doit += newDepense.montant - montant;
                                }
                            }
                        }, function (errorResponse) {
                            _this.constante.traiteErreur(errorResponse, _this);
                        });
                    }
                },
                {
                    text: 'Modifier cette dépense',
                    handler: function () {
                        var toast = _this.toastCtrl.create({
                            message: 'Pas encore implémenté petit scarabée!!!',
                            duration: 3000,
                            position: 'top'
                        });
                        toast.present();
                    }
                },
                {
                    text: 'Ne rien faire',
                    handler: function () {
                    }
                }
            ]
        });
        if (operation.depense.idOperation != null) {
            alert.addButton({
                text: 'Voir les documents',
                handler: function () {
                    // Lecture de l'opération
                    _this.restangular.one('operation/' + operation.depense.idOperation).get().subscribe(function (ope) {
                        var operationAvecDepense = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["j" /* OperationAvecDepense */]();
                        operationAvecDepense.operation = ope;
                        operationAvecDepense.depense = operation.depense;
                        _this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_liste_document_cmy_liste_document__["a" /* ListeDocument */], { theOperation: operationAvecDepense });
                    }, function (errorResponse) {
                        _this.constante.traiteErreur(errorResponse, _this);
                    });
                }
            });
        }
        ;
        alert.present();
    };
    ;
    return ListeDepense;
}());
ListeDepense = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'liste-depense',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-depense\cmy-liste-depense.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Les dépenses</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <H1>Les dépenses</H1>\n  <div class="list-mini">\n    <ion-list>\n      <button class="list-item"  (press)="detailDepense(depense)" ion-item *ngFor="let depense of depenses">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(depense.user.urlAvatar)\'></preload-image>\n          </ion-col>\n          <ion-col col-6 class="item-content">\n            <h3 class="item-title" text-wrap> Dépense : {{depense.depense.commentaire}}</h3>\n            <p class="item-description"> Date : {{depense.depense.date}}</p>\n            <p class="item-description"> Montant : {{depense.depense.montant.toFixed(2)}} €</p>\n            <p class="item-description"> Payé par : {{depense.user.prenom}}</p>\n          </ion-col>\n          <ion-col col-3 class="item-avatar">\n            <preload-image class="avatar-image" *ngIf="depense.depense.urlPhoto" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(depense.depense.urlPhoto)\'></preload-image>\n          </ion-col>\n        </ion-row>\n      </button>\n    </ion-list>\n  </div>\n\n\n  <H1>Les paiements</H1>\n  <div class="list-mini">\n    <ion-list>\n      <button class="list-item"  (press)="detailPaiement(paiement)" ion-item *ngFor="let paiement of paiements">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(paiement.emetteur.urlAvatar)\'></preload-image>\n            <ion-badge *ngIf="paiement.mouvement.etat==\'Transmis\'" class="etat" color="danger">\n              En attente\n            </ion-badge>\n            <ion-badge *ngIf="paiement.mouvement.etat!=\'Transmis\'" class="etat" color="secondary">\n              Payé\n            </ion-badge>\n          </ion-col>\n          <ion-col *ngIf="paiement.mouvement.etat==\'Transmis\'" col-6 class="item-content" >\n            <h3 class="item-title" text-wrap> {{paiement.mouvement.commentaire}}</h3>\n            <p class="item-description"> Date : {{paiement.mouvement.date}}</p>\n            <p class="item-description"> Montant : {{paiement.mouvement.montant.toFixed(2)}} €</p>\n            <p class="item-description" text-wrap> Demandé par {{paiement.destinataire.prenom}} à {{paiement.emetteur.prenom}}</p>\n          </ion-col>\n          <ion-col *ngIf="paiement.mouvement.etat!=\'Transmis\'" col-6 class="item-content">\n            <h3 class="item-title" text-wrap> {{paiement.mouvement.commentaire}}</h3>\n            <p class="item-description"> Date : {{paiement.mouvement.date}}</p>\n            <p class="item-description"> Montant : {{paiement.mouvement.montant.toFixed(2)}} €</p>\n            <p class="item-description" text-wrap> Payé par {{paiement.emetteur.prenom}} à {{paiement.destinataire.prenom}}</p>\n          </ion-col>\n\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(paiement.destinataire.urlAvatar)\'></preload-image>\n          </ion-col>\n        </ion-row>\n      </button>\n    </ion-list>\n  </div>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-depense\cmy-liste-depense.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */]])
], ListeDepense);

var DepenseAvecUser = (function () {
    function DepenseAvecUser() {
    }
    return DepenseAvecUser;
}());
var MouvementAvecUser = (function () {
    function MouvementAvecUser() {
    }
    return MouvementAvecUser;
}());
//# sourceMappingURL=cmy-liste-depense.js.map

/***/ }),

/***/ 398:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AjoutDocumentPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(22);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ionic_native_camera__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__ionic_native_file_transfer__ = __webpack_require__(207);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__ionic_native_file_chooser__ = __webpack_require__(208);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__ionic_native_file_path__ = __webpack_require__(399);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

//import 'rxjs/Rx';








var AjoutDocumentPage = (function () {
    function AjoutDocumentPage(fileChooser, transfer, nav, filePath, loadingCtrl, modalCtrl, constante, restangular, camera, params, toastCtrl) {
        this.fileChooser = fileChooser;
        this.transfer = transfer;
        this.nav = nav;
        this.filePath = filePath;
        this.loadingCtrl = loadingCtrl;
        this.modalCtrl = modalCtrl;
        this.constante = constante;
        this.restangular = restangular;
        this.camera = camera;
        this.params = params;
        this.toastCtrl = toastCtrl;
        this.imageUrl = null;
        this.imageDataCamera = null;
        this.documentName = null;
        this.documentFolder = null;
        this.valid = false;
        this.options = {
            quality: 80,
            sourceType: this.camera.PictureSourceType.CAMERA,
            destinationType: this.camera.DestinationType.DATA_URL,
            encodingType: this.camera.EncodingType.PNG,
            saveToPhotoAlbum: false,
            targetWidth: 200,
            targetHeight: 200
        };
        this.operation = params.get("theOperation");
        this.documents = params.get("theDocuments");
        this.creationDocumentForm = new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* FormGroup */]({
            commentaire: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required
            ]))
        });
    }
    ;
    AjoutDocumentPage.prototype.savePhoto = function () {
        var _this = this;
        var promise = new Promise(function (resolve, reject) {
            // Nouvelle photo en envoyer
            _this.imageUrl = _this.createFileName();
            _this.imageUrl += "==" + _this.imageDataCamera;
            var document = new __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["d" /* Document */]();
            document.type = "Photo";
            document.url = _this.imageUrl;
            document.description = _this.creationDocumentForm.get("commentaire").value;
            document.date = new Date();
            document.idOperation = _this.operation.operation.id;
            _this.restangular.one("document").post("save", document).subscribe(function (resp) {
                _this.documents.push(resp);
                resolve(resp);
            }, function (errorResponse) {
                reject(errorResponse);
            });
        });
        return promise;
    };
    AjoutDocumentPage.prototype.saveOneDocument = function (folder, name) {
        var _this = this;
        var d = new Date();
        var n = d.getTime();
        var newFileName = "document/" + this.constante.user.id + "_" + n + "_" + name;
        var options = {
            fileKey: 'file',
            fileName: newFileName,
            headers: {}
        };
        var promise = new Promise(function (resolve, reject) {
            // Nouvelle photo en envoyer
            var fileTransfer = _this.transfer.create();
            var urlApi = _this.constante.BASE_URL_REST + "/utilitaire/upload";
            var uri = folder + "/" + name;
            fileTransfer.upload(uri, urlApi, options).then(function (rep) {
                var document = new __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["d" /* Document */]();
                document.description = _this.creationDocumentForm.get("commentaire").value;
                document.type = "Doc";
                document.date = new Date();
                document.url = newFileName;
                document.idOperation = _this.operation.operation.id;
                _this.restangular.one("document").post("save", document).subscribe(function (resp) {
                    _this.documents.push(resp);
                    resolve(resp);
                }, function (errorResponse) {
                    reject(errorResponse);
                });
            }, function (errorResponse) {
                reject(errorResponse);
            });
        });
        return promise;
    };
    AjoutDocumentPage.prototype.saveDocument = function () {
        var _this = this;
        var tab = new Array();
        if (this.imageDataCamera != null) {
            tab.push(this.savePhoto());
        }
        ;
        if (this.documentName != null) {
            tab.push(this.saveOneDocument(this.documentFolder, this.documentName));
        }
        this.loading = this.loadingCtrl.create({
            content: 'Enregistrement...',
        });
        this.loading.present();
        Promise.all(tab).then(function (rep) {
            _this.loading.dismissAll();
            _this.nav.pop();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    AjoutDocumentPage.prototype.chooseDocument = function () {
        var _this = this;
        this.fileChooser.open().then(function (rep) {
            var index = rep.lastIndexOf("/");
            _this.documentName = rep.substring(index + 1);
            _this.documentFolder = rep.substring(0, index);
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    AjoutDocumentPage.prototype.takePhoto = function () {
        var _this = this;
        this.options.sourceType = this.camera.PictureSourceType.CAMERA;
        this.camera.getPicture(this.options).then(function (imageData) {
            _this.imageDataCamera = "data:image/png;base64," + imageData;
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    AjoutDocumentPage.prototype.chooseGallery = function () {
        var _this = this;
        this.options.sourceType = this.camera.PictureSourceType.PHOTOLIBRARY;
        this.camera.getPicture(this.options).then(function (imageData) {
            _this.imageDataCamera = "data:image/png;base64," + imageData;
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    // Create a new name for the image
    AjoutDocumentPage.prototype.createFileName = function () {
        var d = new Date();
        var n = d.getTime();
        var newFileName = "document/" + this.constante.user.id + "_" + n + ".png";
        return newFileName;
    };
    ;
    return AjoutDocumentPage;
}());
AjoutDocumentPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'ajout-document-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-ajout-document\cmy-ajout-document.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Ajout document</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="forms-examples-content">\n  <div  class="forms-wrapper">\n    <div  class="post-example-view">\n      <form class="sample-form post-form" [formGroup]="creationDocumentForm" (ngSubmit)="saveDocument()">\n        <section class="form-section">\n          <ion-item>\n            <ion-input type="text" placeholder="Description" formControlName="commentaire"></ion-input>\n          </ion-item>\n          <button ion-button block large  type="button"  class="upload-image-button" >\n            <ion-icon name="folder" (tap)="chooseDocument()"></ion-icon>\n            <h3 class="button-title" *ngIf="documentName!=null">Doc : {{documentName}}</h3>\n            <h3 class="button-title" *ngIf="documentName==null">Choisissez un document</h3>\n          </button>\n          <button ion-button block large  type="button"  class="upload-image-button" >\n            <ion-icon name="camera" (tap)="takePhoto()"></ion-icon><ion-icon name="images" (tap)="chooseGallery()"></ion-icon>\n            <img [src]="imageDataCamera" *ngIf="imageDataCamera" />\n            <img [src]="constante.getUrlImage(imageUrl)" *ngIf="imageUrl" />\n            <h3 class="button-title">Prenez une photo</h3>\n          </button>\n        </section>\n\n        <section class="form-section">\n          <button ion-button block class="form-action-button create-post-button" type="submit" [disabled]="!creationDocumentForm.valid">Enregistrer!</button>\n        </section>\n      </form>\n    </div>\n  </div>\n\n</ion-content>\n\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-ajout-document\cmy-ajout-document.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_7__ionic_native_file_chooser__["a" /* FileChooser */], __WEBPACK_IMPORTED_MODULE_6__ionic_native_file_transfer__["a" /* FileTransfer */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_8__ionic_native_file_path__["a" /* FilePath */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"],
        __WEBPACK_IMPORTED_MODULE_4__ionic_native_camera__["a" /* Camera */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */]])
], AjoutDocumentPage);

;
//# sourceMappingURL=cmy-ajout-document.js.map

/***/ }),

/***/ 400:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ModalOnePhoto; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var ModalOnePhoto = (function () {
    function ModalOnePhoto(platform, params, viewCtrl, constante) {
        this.platform = platform;
        this.params = params;
        this.viewCtrl = viewCtrl;
        this.constante = constante;
        this.photo = this.params.get("thePhoto");
    }
    ModalOnePhoto.prototype.dismiss = function () {
        this.viewCtrl.dismiss();
    };
    return ModalOnePhoto;
}());
ModalOnePhoto = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-one-photo.html"*/'<ion-header>\n  <ion-toolbar>\n    <ion-title>\n      Document\n    </ion-title>\n    <ion-buttons start>\n      <button ion-button (tap)="dismiss()">\n        <span ion-text color="primary" showWhen="ios">Cancel</span>\n        <ion-icon name="md-close" showWhen="android, windows"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-toolbar>\n</ion-header>\n<ion-content class="list-mini-content">\n  <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(photo)\'></preload-image>\n</ion-content>\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-modal\modal-one-photo.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["l" /* Platform */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["o" /* ViewController */],
        __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */]])
], ModalOnePhoto);

//# sourceMappingURL=modal-one-photo.js.map

/***/ }),

/***/ 402:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BilanEvent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ionic_native_sms__ = __webpack_require__(72);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var BilanEvent = (function () {
    function BilanEvent(nav, constante, loadingCtrl, alertCtrl, smsProvider, restangular, alertController, toastCtrl, modalController, navParam) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.alertCtrl = alertCtrl;
        this.smsProvider = smsProvider;
        this.restangular = restangular;
        this.alertController = alertController;
        this.toastCtrl = toastCtrl;
        this.modalController = modalController;
        this.navParam = navParam;
        this.loading = this.loadingCtrl.create();
        this.event = navParam.get("theEvent");
        this.participants = navParam.get("theParticipants");
    }
    BilanEvent.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        this.restangular.all('event/' + this.event.id + '/bilan').getList().subscribe(function (mouvements) {
            _this.mouvements = new Array();
            for (var _i = 0, mouvements_1 = mouvements; _i < mouvements_1.length; _i++) {
                var mouvement = mouvements_1[_i];
                var user1 = _this.rechercheUser(mouvement.idEmetteur);
                var user2 = _this.rechercheUser(mouvement.idDestinataire);
                var mvt = new MouvementAvecUser();
                mvt.mouvement = mouvement;
                mvt.userSource = user1;
                mvt.userCible = user2;
                _this.mouvements.push(mvt);
            }
            _this.loading.dismissAll();
        }, function (errorResponse) {
            _this.loading.dismissAll();
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    BilanEvent.prototype.rechercheUser = function (id) {
        for (var _i = 0, _a = this.participants; _i < _a.length; _i++) {
            var user = _a[_i];
            if (user.user.id == id)
                return user.user;
        }
    };
    BilanEvent.prototype.solderEvent = function () {
        var _this = this;
        if (this.event.roles.indexOf("Createur") < 0) {
            this.constante.presentToast("Seul le créateur peut solder l'event!");
            return;
        }
        ;
        this.loading = this.loadingCtrl.create({
            content: 'Enregistrement...',
        });
        this.loading.present();
        this.restangular.one('event/' + this.event.id + '/validebilan').get().subscribe(function (mouvements) {
            _this.loading.dismissAll();
            _this.event.etat = "En cours de solde";
            _this.presentToast("Les ordres de paiement ont été envoyés!");
        }, function (errorResponse) {
            _this.loading.dismissAll();
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    BilanEvent.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    ;
    BilanEvent.prototype.EnvoyerSms = function () {
        this.chooseCategory();
    };
    ;
    BilanEvent.prototype.chooseCategory = function () {
        var _this = this;
        var alert = this.alertCtrl.create({
            cssClass: 'category-prompt'
        });
        alert.setTitle('Communication');
        alert.addInput({
            type: 'checkbox',
            label: 'Par SMS',
            value: 'sms',
            checked: true
        });
        alert.addInput({
            type: 'checkbox',
            label: 'Par mail',
            value: 'mail'
        });
        alert.addInput({
            type: 'checkbox',
            label: 'Par message CoMoneyty',
            value: 'message'
        });
        alert.addButton('Annule');
        alert.addButton({
            text: 'OK',
            handler: function (data) {
                console.log('Checkbox data:', data);
                var tab = new Array();
                _this.categories_checkbox_open = false;
                _this.categories_checkbox_result = data;
                if (data.indexOf("sms") != -1) {
                    tab.push(_this.sms());
                }
                if (data.indexOf("message") != -1) {
                    tab.push(_this.messageCoMoneyTy());
                }
                if (data.indexOf("mail") != -1) {
                }
                _this.loading = _this.loadingCtrl.create({
                    content: 'Envoi message...',
                });
                _this.loading.present();
                Promise.all(tab).then(function (rep) {
                    _this.loading.dismissAll();
                    _this.presentToast("Messages envoyés!");
                }, function (errorResponse) {
                    _this.loading.dismissAll();
                    _this.constante.traiteErreur(errorResponse, _this);
                });
            }
        });
        alert.present().then(function () {
            _this.categories_checkbox_open = true;
        });
    };
    ;
    BilanEvent.prototype.sms = function () {
        console.log("SMS!");
        var options = {
            replaceLineBreaks: false,
            android: {
                intent: ''
            }
        };
        var tabSMS = new Array();
        for (var _i = 0, _a = this.mouvements; _i < _a.length; _i++) {
            var mouvement = _a[_i];
            var phone = mouvement.userSource.phone;
            if (phone == null)
                continue;
            phone = phone.replace(" ", "");
            tabSMS.push(this.smsProvider.send(phone, 'Salut ' + mouvement.userSource.prenom + '! Tu dois ' + mouvement.mouvement.montant + '€ à ' + mouvement.userCible.prenom + " pour l'event " + this.event.libelle + " du " + this.event.date, options));
        }
        return Promise.all(tabSMS);
    };
    ;
    BilanEvent.prototype.messageCoMoneyTy = function () {
        console.log("message!");
        var tabSMS = new Array();
        for (var _i = 0, _a = this.mouvements; _i < _a.length; _i++) {
            var mouvement = _a[_i];
            var message = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["h" /* Message */]();
            message.emetteur = mouvement.userCible;
            message.destinataire = mouvement.userSource;
            message.date = new Date();
            message.titre = "Tu dois de l'argent!!!";
            message.message = 'Salut ' + mouvement.userSource.prenom + '! Tu dois ' + mouvement.mouvement.montant + '€ à ' + mouvement.userCible.prenom + " pour l'event " + this.event.libelle + " du " + this.event.date;
            message.dejaLu = false;
            var messageRest = this.restangular.copy(message);
            messageRest.route = "message";
            tabSMS.push(messageRest.save().toPromise());
        }
        return Promise.all(tabSMS);
    };
    ;
    return BilanEvent;
}());
BilanEvent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'bilan-event',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-bilan-event\cmy-bilan-event.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Equilibrage</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <ion-item-group>\n    <ion-item class="notification-item" *ngFor="let mouvement of mouvements">\n      <ion-avatar item-left>\n        <preload-image class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(mouvement.userSource.urlAvatar)\'></preload-image>\n      </ion-avatar>\n      <ion-avatar item-right>\n        <preload-image class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(mouvement.userCible.urlAvatar)\'></preload-image>\n      </ion-avatar>\n      <h2 class="item-title" text-wrap>{{mouvement.userSource.prenom}} doit {{mouvement.mouvement.montant}} € à {{mouvement.userCible.prenom}}</h2>\n    </ion-item>\n  </ion-item-group>\n\n  <section>\n    <button ion-button block padding class="form-action-button create-post-button" (tap)="EnvoyerSms()" type="button">Relancer vos amis!</button>\n  </section>\n  <section>\n    <button ion-button block padding class="form-action-button create-post-button" (tap)="solderEvent()" type="button" [disabled]="event.etat!=\'Ouvert\'">Solder l\'event!</button>\n  </section>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-bilan-event\cmy-bilan-event.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_4__ionic_native_sms__["a" /* SMS */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */]])
], BilanEvent);

;
var MouvementAvecUser = (function () {
    function MouvementAvecUser() {
    }
    return MouvementAvecUser;
}());
//# sourceMappingURL=cmy-bilan-event.js.map

/***/ }),

/***/ 403:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CreationEventPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(22);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ionic_native_camera__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_photo__ = __webpack_require__(205);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

//import 'rxjs/Rx';






var CreationEventPage = (function () {
    function CreationEventPage(nav, loadingCtrl, modalCtrl, constante, restangular, alertCtrl, camera, params, toastCtrl) {
        this.nav = nav;
        this.loadingCtrl = loadingCtrl;
        this.modalCtrl = modalCtrl;
        this.constante = constante;
        this.restangular = restangular;
        this.alertCtrl = alertCtrl;
        this.camera = camera;
        this.params = params;
        this.toastCtrl = toastCtrl;
        this.imageUrl = null;
        this.imageDataCamera = null;
        this.options = {
            quality: 80,
            sourceType: this.camera.PictureSourceType.CAMERA,
            destinationType: this.camera.DestinationType.DATA_URL,
            encodingType: this.camera.EncodingType.PNG,
            saveToPhotoAlbum: false,
            targetWidth: 200,
            targetHeight: 200
        };
        this.creationEventForm = new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* FormGroup */]({
            titre: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required
            ])),
            date: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */](new Date().toISOString(), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required
            ])),
        });
        this.event = new __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["e" /* Event */]();
        this.events = params.get("listeEvent");
    }
    ;
    // Create a new name for the image
    CreationEventPage.prototype.createFileName = function () {
        var d = new Date();
        var n = d.getTime();
        var newFileName = "event/" + this.constante.user.id + "_" + n + ".png";
        return newFileName;
    };
    ;
    CreationEventPage.prototype.saveEvent = function () {
        var _this = this;
        this.event.libelle = this.creationEventForm.get('titre').value;
        this.event.date = this.creationEventForm.get('date').value;
        if (this.imageDataCamera == null) {
            // Pas de nouvelle photo
            if (this.imageUrl == null) {
                this.imageUrl = 'event/standard.png';
            }
        }
        else {
            // Nouvelle photo en envoyer
            this.imageUrl = this.createFileName();
            this.imageUrl += "==" + this.imageDataCamera;
        }
        this.event.urlPhoto = this.imageUrl;
        this.loading = this.loadingCtrl.create({
            content: 'Enregistrement...',
        });
        this.loading.present();
        var eventRest = this.restangular.copy(this.event);
        eventRest.route = "event";
        eventRest.save().toPromise().then(function (resp) {
            _this.loading.dismissAll();
            _this.events.push(resp);
            _this.loading.dismissAll();
            _this.nav.pop();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    CreationEventPage.prototype.chooseCategory = function () {
        var _this = this;
        var alert = this.alertCtrl.create({
            cssClass: 'category-prompt'
        });
        alert.setTitle('Categorie');
        alert.addInput({
            type: 'checkbox',
            label: 'Sortie',
            value: 'sortie',
            checked: true
        });
        alert.addInput({
            type: 'checkbox',
            label: 'Soirée',
            value: 'soirée'
        });
        alert.addInput({
            type: 'checkbox',
            label: 'Coloc',
            value: 'coloc'
        });
        alert.addButton('Annule');
        alert.addButton({
            text: 'OK',
            handler: function (data) {
                console.log('Checkbox data:', data);
                _this.categories_checkbox_open = false;
                _this.categories_checkbox_result = data;
            }
        });
        alert.present().then(function () {
            _this.categories_checkbox_open = true;
        });
    };
    ;
    CreationEventPage.prototype.choosePhoto = function () {
        var _this = this;
        var modal = this.modalCtrl.create(__WEBPACK_IMPORTED_MODULE_5__cmy_modal_modal_photo__["a" /* ModalPhoto */]);
        modal.onDidDismiss(function (data) {
            console.log(data);
            _this.imageUrl = data;
        });
        modal.present();
    };
    ;
    CreationEventPage.prototype.takePhoto = function () {
        var _this = this;
        this.options.sourceType = this.camera.PictureSourceType.CAMERA;
        this.camera.getPicture(this.options).then(function (imageData) {
            _this.imageDataCamera = "data:image/png;base64," + imageData;
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    CreationEventPage.prototype.chooseGallery = function () {
        var _this = this;
        this.options.sourceType = this.camera.PictureSourceType.PHOTOLIBRARY;
        this.camera.getPicture(this.options).then(function (imageData) {
            _this.imageDataCamera = "data:image/png;base64," + imageData;
        }, function (err) {
            _this.constante.traiteErreur(err, _this);
        });
    };
    ;
    CreationEventPage.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    ;
    return CreationEventPage;
}());
CreationEventPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'creation-event-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-creation-event\cmy-creation-event.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Nouvel Event</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="forms-examples-content">\n  <div  class="forms-wrapper">\n    <div  class="post-example-view">\n  <form class="sample-form post-form" [formGroup]="creationEventForm" (ngSubmit)="saveEvent()">\n    <section class="form-section">\n      <ion-item>\n        <ion-input type="text" placeholder="Titre de l\'event" formControlName="titre"></ion-input>\n      </ion-item>\n      <button ion-button block large  type="button"  class="upload-image-button" >\n        <ion-icon name="camera" (tap)="takePhoto()"></ion-icon><ion-icon name="folder" (tap)="choosePhoto()"></ion-icon><ion-icon name="images" (tap)="chooseGallery()"></ion-icon>\n        <img [src]="imageDataCamera" *ngIf="imageDataCamera" />\n        <img [src]="constante.getUrlImage(imageUrl)" *ngIf="imageUrl" />\n        <h3 class="button-title">Prenez une photo</h3>\n      </button>\n    </section>\n    <section class="form-section">\n      <button ion-button block icon-right  type="button" class="choose-category-button" (tap)="chooseCategory()">\n        Choisissez le type d\'event\n        <ion-icon name="add"></ion-icon>\n      </button>\n    </section>\n    <section class="form-section">\n      <ion-item class="multi-input time-item">\n        <ion-label floating>Date</ion-label>\n        <ion-datetime formControlName="date" displayFormat="DD/MM/YYYY" pickerFormat="DD/MM/YYYY"></ion-datetime>\n      </ion-item>\n    </section>\n    <section class="form-section">\n      <button ion-button block class="form-action-button create-post-button" type="submit" [disabled]="!creationEventForm.valid">Enregistrer!</button>\n    </section>\n  </form>\n    </div>\n  </div>\n\n</ion-content>\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-creation-event\cmy-creation-event.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_6_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */],
        __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_6_ngx_restangular__["Restangular"],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */],
        __WEBPACK_IMPORTED_MODULE_4__ionic_native_camera__["a" /* Camera */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */]])
], CreationEventPage);

//# sourceMappingURL=cmy-creation-event.js.map

/***/ }),

/***/ 404:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeHistorique; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




//import 'rxjs/Rx';
var ListeHistorique = (function () {
    function ListeHistorique(nav, constante, loadingCtrl, navParams, alertCtrl, restangular, alertController) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.navParams = navParams;
        this.alertCtrl = alertCtrl;
        this.restangular = restangular;
        this.alertController = alertController;
        this.loading = this.loadingCtrl.create();
        this.event = this.navParams.get("theEvent");
    }
    ;
    ListeHistorique.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // This will query /accounts and return a observable.
        this.restangular.all('event/' + this.event.id + '/historique').getList().subscribe(function (histo) {
            console.log(histo);
            _this.historiques = histo;
            _this.loading.dismissAll();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    return ListeHistorique;
}());
ListeHistorique = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'liste-historique',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-historique\cmy-liste-historique.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Historique de l\'event</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <ion-item-group>\n      <ion-item  class="notification-item" ion-item *ngFor="let historique of historiques">\n        <ion-avatar item-left>\n          <preload-image class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(historique.user.urlAvatar)\'></preload-image>\n        </ion-avatar>\n        <h2 class="item-title">{{historique.timestamp}} - {{historique.user.nom}} {{historique.user.prenom}}</h2>\n        <p class="item-description" text-wrap>{{historique.action}}</p>\n      </ion-item>\n  </ion-item-group>\n</ion-content>\n\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-historique\cmy-liste-historique.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */]])
], ListeHistorique);

//# sourceMappingURL=cmy-liste-historique.js.map

/***/ }),

/***/ 409:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return InvitationAmi; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ionic_native_contacts__ = __webpack_require__(410);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__ionic_native_sms__ = __webpack_require__(72);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






//import 'rxjs/Rx';
var InvitationAmi = (function () {
    function InvitationAmi(nav, constante, loadingCtrl, smsProvider, toastCtrl, restangular, params, platform, contacts_tel) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.smsProvider = smsProvider;
        this.toastCtrl = toastCtrl;
        this.restangular = restangular;
        this.params = params;
        this.platform = platform;
        this.contacts_tel = contacts_tel;
        this.loading = this.loadingCtrl.create();
        this.invitations = params.get("theInvitations");
    }
    InvitationAmi.prototype.ionViewDidLoad = function () {
        var _this = this;
        if (this.platform.is('mobileweb') || this.platform.is('core')) {
            this.contactsComplet = new Array();
            this.loading.present();
            this.restangular.all('user').getList().subscribe(function (users) {
                for (var _i = 0, users_1 = users; _i < users_1.length; _i++) {
                    var usr = users_1[_i];
                    var user = usr;
                    var aContact = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["b" /* Contact */](user.nom, user.prenom, user.phone);
                    aContact.photo = user.urlAvatar;
                    aContact.email = user.email;
                    _this.contactsComplet.push(aContact);
                }
                _this.initContacts();
                _this.loading.dismiss();
            }, function (errorResponse) {
                _this.loading.dismiss();
                _this.constante.traiteErreur(errorResponse, _this);
            });
        }
        else {
            // récupération de toutes les relations
            // options.desiredFields=[ this.contacts_tel.fieldType.id];
            //options.hasPhoneNumber = true;
            var fields = ['displayName', 'phoneNumbers', 'emails', 'photos'];
            var options = new __WEBPACK_IMPORTED_MODULE_4__ionic_native_contacts__["a" /* ContactFindOptions */]();
            options.filter = "";
            options.multiple = true;
            options.desiredFields = fields;
            this.loading.present();
            this.contacts_tel.find(['displayName', 'phoneNumbers', 'emails', 'photos'], options).then(function (res) {
                console.log(res.length);
                _this.contactsComplet = new Array();
                for (var _i = 0, res_1 = res; _i < res_1.length; _i++) {
                    var cont = res_1[_i];
                    var aContact = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["b" /* Contact */]("", "", "");
                    aContact.displayName = cont.displayName;
                    aContact.idInterne = cont.id;
                    aContact.phoneNumber = "";
                    if (cont.phoneNumbers != null) {
                        for (var _a = 0, _b = cont.phoneNumbers; _a < _b.length; _a++) {
                            var obj1 = _b[_a];
                            if (obj1.pref)
                                aContact.phoneNumber = obj1.value;
                        }
                        if (aContact.phoneNumber == "" && cont.phoneNumbers.length > 0)
                            aContact.phoneNumber = cont.phoneNumbers[0].value;
                    }
                    aContact.email = "";
                    if (cont.emails != null) {
                        for (var _c = 0, _d = cont.emails; _c < _d.length; _c++) {
                            var obj1 = _d[_c];
                            if (obj1.pref)
                                aContact.email = obj1.value;
                        }
                        if (aContact.email == "" && cont.emails.length > 0)
                            aContact.email = cont.emails[0].value;
                    }
                    aContact.photo = "";
                    if (cont.photos != null) {
                        for (var _e = 0, _f = cont.photos; _e < _f.length; _e++) {
                            var obj1 = _f[_e];
                            if (obj1.pref)
                                aContact.photo = obj1.value;
                        }
                        if (aContact.photo == "" && cont.photos.length > 0)
                            aContact.photo = cont.photos[0].value;
                    }
                    //    if (aContact.photo == "")
                    //     aContact.photo = "user/inconnu.png";
                    _this.contactsComplet.push(aContact);
                }
                _this.initContacts();
                _this.loading.dismissAll();
            }, function (err) {
                _this.constante.traiteErreur(err, _this);
            });
        }
    };
    ;
    InvitationAmi.prototype.initContacts = function () {
        this.contacts = new Array();
        for (var _i = 0, _a = this.contactsComplet; _i < _a.length; _i++) {
            var contact = _a[_i];
            contact.dejaInvite = false;
            for (var _b = 0, _c = this.invitations; _b < _c.length; _b++) {
                var invite = _c[_b];
                if (invite.contact.idInterne == contact.idInterne) {
                    contact.dejaInvite = true;
                    break;
                }
            }
            this.contacts.push(contact);
        }
    };
    ;
    InvitationAmi.prototype.envoiInvitation = function (contact) {
        var _this = this;
        if (contact.dejaInvite) {
            var toast = this.toastCtrl.create({
                message: "Cet ami a déjà été invité!",
                duration: 3000,
                position: 'top'
            });
            toast.present();
            return;
        }
        if (!(this.platform.is('mobileweb') || this.platform.is('core'))) {
            var options = {
                replaceLineBreaks: false,
                android: {
                    intent: ''
                }
            };
            var message = this.constante.user.prenom + " t'invite sur CoMoneyTy. Click sur le lien ci-dessous pour obtenir l'app : https://lc.cx/f3Sz";
            this.smsProvider.send(contact.phoneNumber, message, options).then(function (rep) {
                var toast = _this.toastCtrl.create({
                    message: "Demande transmise!",
                    duration: 3000,
                    position: 'top'
                });
                toast.present();
            }, function (err) {
                console.log("Error : " + err);
            });
        }
        console.log("invitation...");
        var invitation = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["f" /* Invitation */]();
        invitation.etatReponse = "Invitation envoyée";
        invitation.idUser = this.constante.user.id;
        invitation.contact = contact;
        var invit = this.restangular.copy(invitation);
        invit.route = 'invitation';
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        invit.save().toPromise().then(function (resp) {
            _this.loading.dismissAll();
            contact.dejaInvite = true;
            _this.invitations.push(resp);
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    InvitationAmi.prototype.filtreContact = function (ev) {
        console.log('Filtre');
        var val = ev.target.value;
        // if the value is an empty string don't filter the items
        if (val && val.trim() != '') {
            this.contacts = this.contactsComplet.filter(function (item) {
                return (JSON.stringify(item).toLocaleLowerCase().indexOf(val.toLowerCase()) > -1);
            });
        }
        else {
            this.contacts = this.contactsComplet;
        }
    };
    ;
    return InvitationAmi;
}());
InvitationAmi = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'invitation-ami',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-invitation-ami\cmy-invitation-ami.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Inviter un contact</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <ion-searchbar (ionInput)="filtreContact($event)"></ion-searchbar>\n  <ion-list >\n    <button  (press)="envoiInvitation(contact)" ion-item *ngFor="let contact of contacts">\n      <ion-row align-items-center justifiy-content-end no-padding [ngClass]="{ \'DejaInvite\':contact.dejaInvite}">\n        <ion-col no-padding col-3 class="item-avatar" *ngIf="contact.photo">\n          <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(contact.photo)\'></preload-image>\n        </ion-col>\n        <ion-col class="libelle" col-9>\n          <h3>{{contact.displayName}}</h3>\n          <p><span *ngIf="contact.phoneNumber">{{contact.phoneNumber}}</span> - <span *ngIf="contact.email">{{contact.email}}</span></p>\n        </ion-col>\n      </ion-row>\n    </button>\n  </ion-list>\n\n\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-invitation-ami\cmy-invitation-ami.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_5__ionic_native_sms__["a" /* SMS */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["l" /* Platform */], __WEBPACK_IMPORTED_MODULE_4__ionic_native_contacts__["b" /* Contacts */]])
], InvitationAmi);

//# sourceMappingURL=cmy-invitation-ami.js.map

/***/ }),

/***/ 411:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DetailAmi; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




//import 'rxjs/Rx';
var DetailAmi = (function () {
    function DetailAmi(loadingCtrl, constante, restangular, navParams) {
        this.loadingCtrl = loadingCtrl;
        this.constante = constante;
        this.restangular = restangular;
        this.navParams = navParams;
        this.loading = this.loadingCtrl.create();
        this.ami = navParams.get("theAmi");
    }
    DetailAmi.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // Lecture des participants de cet event
        this.restangular.one('user/' + this.constante.user.id + '/detailAmi/' + this.ami.id).get().toPromise().then(function (rep) {
            console.log(rep);
            _this.events = rep.Event;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    return DetailAmi;
}());
DetailAmi = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'detail-ami',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-ami\cmy-detail-ami.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle >\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title> {{ ami.prenom}} {{ ami.nom}}</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <div class="list-mini">\n    <ion-list>\n      <button class="list-item" (press)="showMenu(event)"  (tap)="open(event)" ion-item *ngFor="let event of events">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(event.urlPhoto)\'></preload-image>\n          </ion-col>\n          <ion-badge class="etat" *ngIf="event.etat==\'En cours de solde\'" color="danger">\n            En Clôture\n          </ion-badge>\n          <ion-badge class="etat" *ngIf="event.etat==\'Annulé\'" color="primary">\n            Annulé\n          </ion-badge>\n          <ion-badge class="etat" *ngIf="event.etat==\'Clos\'" color="secondary">\n            Clos!\n          </ion-badge>\n          <ion-col no-padding col-8 class="item-content">\n            <h3 class="item-title"> {{event.libelle}}</h3>\n            <p class="item-description"> Date : {{event.date}}</p>\n            <p class="item-description" *ngIf="event.montantDu>0"> Je dois {{event.montantDu.toFixed(2)}} € à {{ami.prenom}}</p>\n            <p class="item-description" *ngIf="event.montantDepense>0"> {{ami.prenom}} me doit {{event.montantDepense.toFixed(2)}} €</p>\n            <p class="item-description" *ngIf="event.montantDepense<0.1 && event.montantDu<0.1"> On ne se doit rien</p>\n          </ion-col>\n          <ion-col no-padding col-1 >\n            <ion-icon name="warning"  style="color : red;" *ngIf="event.montantDu>0"></ion-icon>\n          </ion-col>\n        </ion-row>\n      </button>\n    </ion-list>\n  </div>\n</ion-content>\n\n\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-ami\cmy-detail-ami.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */]])
], DetailAmi);

//# sourceMappingURL=cmy-detail-ami.js.map

/***/ }),

/***/ 412:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DetailMessage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_paiement_ordre_cmy_paiement_ordre__ = __webpack_require__(218);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_detail_ordre_cmy_detail_ordre__ = __webpack_require__(219);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






//import 'rxjs/Rx';
var DetailMessage = (function () {
    function DetailMessage(nav, constante, navParams, loadingCtrl, alertController, restangular) {
        this.nav = nav;
        this.constante = constante;
        this.navParams = navParams;
        this.loadingCtrl = loadingCtrl;
        this.alertController = alertController;
        this.restangular = restangular;
        this.message = this.navParams.get("theMessage");
    }
    DetailMessage.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.message.dejaLu = true;
        var msgRest = this.restangular.copy(this.message);
        msgRest.route = "message";
        msgRest.save().toPromise().then(function (rep) {
            console.log("Message bascule en lu");
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    DetailMessage.prototype.traite = function () {
        var message = this.message;
        var messageCache = JSON.parse(message.messageCache);
        if (messageCache.nomClasse == "Invitation") {
            // C'est une invitation
            var invitation = messageCache;
            if (invitation.etatReponse == "Invitation envoyée") {
                this.traiteInvitation(message, invitation);
            }
            else {
                this.constante.presentToast("Invitation déjà acceptée!");
            }
        }
        else if (messageCache.nomClasse == "Ordre") {
            // On est sur un mouvement => On renvoie sur l'ordre
            var ordre = messageCache;
            if (!message.actionRealise)
                this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_paiement_ordre_cmy_paiement_ordre__["a" /* PaiementOrdre */], { theOrdre: ordre, theMessage: message });
            else {
                ordre.mouvement.etat = "Réalisé";
                this.nav.push(__WEBPACK_IMPORTED_MODULE_5__cmy_detail_ordre_cmy_detail_ordre__["a" /* DetailOrdre */], { theOrdre: ordre });
            }
        }
    };
    ;
    DetailMessage.prototype.traiteInvitation = function (message, invitation) {
        var _this = this;
        var alert = this.alertController.create({
            title: 'Accepter une invitation',
            message: "Confirmez-vous cette invitation",
            buttons: [
                {
                    text: 'Oui',
                    role: 'cancel',
                    handler: function () {
                        // Transofrmation de l'invit en relation
                        _this.loading = _this.loadingCtrl.create();
                        _this.loading.present();
                        //let messageCache: Message = JSON.parse(message.messageCache);
                        _this.restangular.one("invitation/" + invitation.id + "/confirm").get().subscribe(function (resp) {
                            console.log("OK!");
                            _this.loading.dismissAll();
                            message.dejaLu = true;
                            message.actionRealise = true;
                            var msgRest = _this.restangular.copy(_this.message);
                            msgRest.route = "message";
                            msgRest.save().toPromise().then(function (rep) {
                                console.log("Message action réalisée!");
                            }, function (error) {
                                _this.constante.traiteErreur(error, _this);
                            });
                        }, function (errorResponse) {
                            _this.constante.traiteErreur(errorResponse, _this);
                        });
                    }
                },
                {
                    text: 'Non',
                    handler: function () {
                        console.log("Abandon");
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    return DetailMessage;
}());
DetailMessage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'detail-message',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-message\cmy-detail-message.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle >\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Message de {{message.emetteur.prenom}}</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content>\n  <ion-item-group>\n    <ion-item>\n      <ion-avatar item-left>\n        <preload-image class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(message.emetteur.urlAvatar)\'></preload-image>\n      </ion-avatar>\n      <h2 class="item-title">{{message.emetteur.nom}} {{message.emetteur.prenom}}</h2>\n      <p class="item-description"  text-nowrap>{{message.titre}}</p>\n      <p class="item-description"  text-wrap>{{message.message}}</p>\n      <ion-note class="item-time" item-right>{{message.date}}</ion-note>\n    </ion-item>\n  </ion-item-group>\n  <section *ngIf="message.messageCache && !message.actionRealise" padding>\n    <button ion-button block class="form-action-button create-post-button" padding (tap)="traite()" >Traiter le message</button>\n  </section>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-message\cmy-detail-message.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]])
], DetailMessage);

//# sourceMappingURL=cmy-detail-message.js.map

/***/ }),

/***/ 414:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ForgotPasswordPage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(22);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ionic_native_sms__ = __webpack_require__(72);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__login_login__ = __webpack_require__(173);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var ForgotPasswordPage = (function () {
    function ForgotPasswordPage(nav, restangular, loadingCtrl, toastCtrl, constante, smsProvider) {
        this.nav = nav;
        this.restangular = restangular;
        this.loadingCtrl = loadingCtrl;
        this.toastCtrl = toastCtrl;
        this.constante = constante;
        this.smsProvider = smsProvider;
        this.main_page = { component: __WEBPACK_IMPORTED_MODULE_6__login_login__["a" /* LoginPage */] };
        this.forgot_password = new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* FormGroup */]({
            email: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(5), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].pattern("[a-z0-9.-_]+@[a-z.]+")])),
            phone: new __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormControl */]('', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].minLength(10), __WEBPACK_IMPORTED_MODULE_2__angular_forms__["g" /* Validators */].pattern("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$")]))
        });
    }
    ;
    ForgotPasswordPage.prototype.recoverPassword = function () {
        var _this = this;
        var phone = this.forgot_password.get('phone').value;
        var email = this.forgot_password.get('email').value;
        // Vérification email et téléphone
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        this.restangular.one("utilitaire").post("controleRecoverPassword", email + "<-->" + phone).toPromise().then(function (rep) {
            _this.loading.dismissAll();
            var message = "Votre mot de passe CoMoneyTy est : " + rep.password;
            _this.smsProvider.send(phone, message, options).then(function (rep) {
                var toast = _this.toastCtrl.create({
                    message: "Votre password a été envoyé par SMS!",
                    duration: 3000,
                    position: 'top'
                });
                toast.present();
            }, function (error) {
                _this.constante.traiteErreur(error, _this);
            });
            return;
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
        var options = {
            replaceLineBreaks: false,
            android: {
                intent: ''
            }
        };
        this.nav.setRoot(this.main_page.component);
    };
    ;
    ForgotPasswordPage.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    ;
    return ForgotPasswordPage;
}());
ForgotPasswordPage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'forgot-password-page',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\forgot-password\forgot-password.html"*/'<ion-header class="forgot-password-header auth-header">\n  <ion-navbar>\n    <ion-title>Mot de passe oublié?</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="forgot-password-content auth-content">\n  <h2 class="auth-title">Récupérez votre password</h2>\n  <p class="recover-message">\n    Entrez votre mail et votre numéro de téléphone pour recevoir votre password...\n  </p>\n  <form class="forgot-password-form auth-form" [formGroup]="forgot_password" (ngSubmit)="recoverPassword()">\n    <ion-item>\n      <ion-input type="email" placeholder="Email" formControlName="email"></ion-input>\n    </ion-item>\n    <ion-item>\n      <ion-input type="tel" placeholder="Téléphone" formControlName="phone"></ion-input>\n    </ion-item>\n    <button ion-button block class="auth-action-button recover-password-button" type="submit" [disabled]="!forgot_password.valid">Récupérer le password</button>\n  </form>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\forgot-password\forgot-password.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */], __WEBPACK_IMPORTED_MODULE_5__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_4__ionic_native_sms__["a" /* SMS */]])
], ForgotPasswordPage);

//# sourceMappingURL=forgot-password.js.map

/***/ }),

/***/ 415:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MasterHome; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_home_cmy_home__ = __webpack_require__(82);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var MasterHome = (function () {
    function MasterHome(nav, constante, loadingCtrl, restangular, alertController, toastCtrl) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.alertController = alertController;
        this.toastCtrl = toastCtrl;
    }
    ;
    MasterHome.prototype.ionViewDidLoad = function () {
    };
    ;
    MasterHome.prototype.goToPartage = function () {
        this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_4__cmy_home_cmy_home__["a" /* Home */]);
    };
    ;
    MasterHome.prototype.goToCommunaute = function () {
        this.constante.presentToast("Bientôt disponible!");
    };
    ;
    MasterHome.prototype.goToDemo = function () {
        this.constante.presentToast("Bientôt disponible!");
    };
    ;
    MasterHome.prototype.goToCagnotte = function () {
        this.constante.presentToast("Bientôt disponible!");
    };
    ;
    return MasterHome;
}());
MasterHome = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'master-home',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\master\master-home.html"*/'\n<ion-header>\n  <ion-navbar>\n    <ion-title> Bienvenue</ion-title>\n  </ion-navbar>\n</ion-header>\n<ion-content class="home">\n\n\n  <section >\n    <ion-row >\n      <ion-col col-6><div text-wrap (tap)="goToCagnotte()" class="drop-shadow notdispo">\n        <p>Cagnotte</p><br>\n        <preload-image class="avatar-image" [ratio]="{w:1, h:1}" src="./assets/images/master/cagnotte.png"></preload-image>\n      </div></ion-col>\n      <ion-col col-6><div text-wrap (tap)="goToPartage()" class="drop-shadow ">\n        <p>Partage des dépenses</p>\n        <preload-image class="avatar-image" [ratio]="{w:1, h:1}" src="./assets/images/master/partage.png"></preload-image>\n        </div></ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-6><div text-wrap (tap)="goToCommunaute()" class="drop-shadow notdispo">\n        <p>Communauté</p><br>\n        <preload-image class="avatar-image" [ratio]="{w:1, h:1}" src="./assets/images/master/communaute.png"></preload-image>\n        </div></ion-col>\n      <ion-col col-6><div text-wrap (tap)="goToDemo()" class="drop-shadow notdispo">\n        <p>Démo</p><br>\n        <preload-image class="avatar-image" [ratio]="{w:1, h:1}" src="./assets/images/master/communaute.png"></preload-image>\n        </div></ion-col>\n    </ion-row>\n    <ion-item>\n      <div text-wrap class="drop-shadow notdispo" style="width: 85%;">\n        <p>Mon accès MyBank </p>\n        <preload-image class="avatar-image" [ratio]="{w:402, h:125}" src="./assets/images/master/neo.png"></preload-image>\n      </div>\n    </ion-item>\n  </section>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\master\master-home.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["n" /* ToastController */]])
], MasterHome);

//# sourceMappingURL=master-home.js.map

/***/ }),

/***/ 416:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeOperation; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_detail_operation_cmy_detail_operation__ = __webpack_require__(417);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_modal_modal_choix_event__ = __webpack_require__(216);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__ = __webpack_require__(124);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__cmy_liste_document_cmy_liste_document__ = __webpack_require__(206);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








//import 'rxjs/Rx';
var ListeOperation = (function () {
    function ListeOperation(nav, constante, loadingCtrl, restangular, modalController) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.modalController = modalController;
        this.visible = false;
        this.loading = this.loadingCtrl.create();
    }
    ListeOperation.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        this.restangular.all('user/' + this.constante.user.id + '/operations').getList().subscribe(function (operations) {
            _this.tableauOperations = operations;
            _this.tableauOperationsInitial = operations;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.loading.dismiss();
            _this.constante.traiteErreur(errorResponse, _this);
        });
        var sousmenus = new Array();
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Affecter", this.transfert, "share-alt"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Detail", this.detail, "open"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Document", this.ajoutDocument, "clipboard"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Quitter", this.closeMenu, "close"));
        this.menu.config(sousmenus);
    };
    ;
    ListeOperation.prototype.detail = function (operation) {
        this.closeMenu();
        this.nav.push(__WEBPACK_IMPORTED_MODULE_3__cmy_detail_operation_cmy_detail_operation__["a" /* DetailOperation */], { theOperation: operation });
    };
    ;
    ListeOperation.prototype.ajoutDocument = function (operation) {
        this.closeMenu();
        this.nav.push(__WEBPACK_IMPORTED_MODULE_7__cmy_liste_document_cmy_liste_document__["a" /* ListeDocument */], { theOperation: operation });
    };
    ;
    ListeOperation.prototype.transfert = function (operationAvecDepense) {
        var _this = this;
        this.closeMenu();
        if (operationAvecDepense.depense != null) {
            this.constante.presentToast("Opération déjà utilisée!");
            console.log("return transfert");
            return;
        }
        if (operationAvecDepense.operation.montant > 0) {
            this.constante.presentToast("Merci de sélectionner un débit uniquement!");
            return;
        }
        var operation = operationAvecDepense.operation;
        var modal = this.modalController.create(__WEBPACK_IMPORTED_MODULE_4__cmy_modal_modal_choix_event__["a" /* ModalChoixEvent */]);
        modal.onDidDismiss(function (event) {
            if (event == null) {
                return;
            }
            _this.loading = _this.loadingCtrl.create({
                content: 'Enregistrement...',
            });
            _this.loading.present();
            var depense = new __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["c" /* Depense */](_this.constante.user.id, event.id);
            depense.idOperation = operation.id;
            depense.montant = -operation.montant;
            depense.commentaire = operation.description;
            depense.date = operation.date;
            _this.restangular.one("depense").post("save", depense).subscribe(function (resp) {
                // Ajout à la liste
                _this.loading.dismissAll();
                console.log("dépense sauvée");
                depense.id = resp.id;
                operationAvecDepense.depense = depense;
            }, function (errorResponse) {
                _this.constante.traiteErreur(errorResponse, _this);
            });
        });
        modal.present();
    };
    ;
    ListeOperation.prototype.closeMenu = function () {
        this.visible = false;
        this.menu.toggle();
        this.menu.close();
    };
    ;
    ListeOperation.prototype.blockEvent = function () {
        console.log("Il faut bloquer!!!");
        //    this.parent.cover.nativeElement.style.display="none";
        this.visible = false;
    };
    ;
    ListeOperation.prototype.showMenu = function (operationAvecDepense) {
        this.visible = true;
        this.menu.show(this, operationAvecDepense, "divers/operation.png");
        this.menu.toggle();
    };
    ;
    ListeOperation.prototype.filtreOperation = function (ev) {
        console.log('Filtre');
        var val = ev.target.value;
        // if the value is an empty string don't filter the items
        if (val && val.trim() != '') {
            this.tableauOperations = this.tableauOperationsInitial.filter(function (item) {
                return (JSON.stringify(item).toLocaleLowerCase().indexOf(val.toLowerCase()) > -1);
            });
        }
        else {
            this.tableauOperations = this.tableauOperationsInitial;
        }
    };
    ;
    return ListeOperation;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('menu'),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["a" /* MenuCircular */])
], ListeOperation.prototype, "menu", void 0);
ListeOperation = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'liste-operation',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-operation\cmy-liste-operation.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Mes opérations</ion-title>\n  </ion-navbar>\n</ion-header>\n\n\n\n<ion-content class="notifications-content">\n  <div #cover id="cover" [ngClass]="{\'invisible\':!visible}" (tap)="blockEvent()" ion-fixed></div>\n  <menu-circular #menu class="circular-menu" [ngClass]="{\'invisible\':!visible}" ion-fixed></menu-circular>\n  <ion-searchbar (ionInput)="filtreOperation($event)"></ion-searchbar>\n  <ion-item-group>\n    <div *ngFor="let tab of tableauOperations">\n      <ion-item-divider class="notifications-divider">{{tab.titre}}</ion-item-divider>\n      <ion-item (tap)="showMenu(operationAvecDepense)"   [ngClass]="{ \'AvecDepense\':operationAvecDepense.depense!=null}" ion-item *ngFor="let operationAvecDepense of tab.tableau">\n         <div style="display:flex">\n           <div style="flex:1" text-wrap>{{operationAvecDepense.operation.date.substring(0,5)}} {{operationAvecDepense.operation.description}}  <ion-icon *ngIf="operationAvecDepense.operation.presenceDocument" name="attach"></ion-icon></div>\n           <div style="width: 30px;margin-right: 10px;" *ngIf="operationAvecDepense.urlPhoto"><preload-image style="border-radius: 50%" class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(operationAvecDepense.urlPhoto)\'></preload-image></div>\n           <div [ngClass]="{\'negatif\':operationAvecDepense.operation.montant<0,\'positif\':operationAvecDepense.operation.montant>=0}">{{operationAvecDepense.operation.montant.toFixed(2)}} €</div>\n         </div>\n      </ion-item>\n    </div>\n  </ion-item-group>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-operation\cmy-liste-operation.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["h" /* ModalController */]])
], ListeOperation);

;
//# sourceMappingURL=cmy-liste-operation.js.map

/***/ }),

/***/ 417:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DetailOperation; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




//import 'rxjs/Rx';
var DetailOperation = (function () {
    function DetailOperation(nav, constante, navParams, loadingCtrl, alertController, restangular) {
        this.nav = nav;
        this.constante = constante;
        this.navParams = navParams;
        this.loadingCtrl = loadingCtrl;
        this.alertController = alertController;
        this.restangular = restangular;
        this.operationAvecDepense = this.navParams.get("theOperation");
        this.loading = this.loadingCtrl.create();
    }
    DetailOperation.prototype.ionViewDidLoad = function () {
        var _this = this;
        if (this.operationAvecDepense.depense != null) {
            this.loading.present();
            // This will query /accounts and return a observable.
            this.restangular.one('event/' + this.operationAvecDepense.depense.idEvent).doGET().subscribe(function (event) {
                _this.event = event;
                _this.loading.dismiss();
            }, function (errorResponse) {
                _this.constante.traiteErreur(errorResponse, _this);
            });
        }
    };
    DetailOperation.prototype.supprimeLien = function () {
        var _this = this;
        var alert = this.alertController.create({
            title: 'Confirmer la suppression',
            message: "Vous voulez retirer cette opération de l'event?",
            buttons: [
                {
                    text: 'Non',
                    role: 'cancel',
                    handler: function () {
                        console.log('Cancel clicked');
                    }
                },
                {
                    text: 'Oui',
                    handler: function () {
                        // suppression du lien
                        console.log('OK clicked');
                        _this.loading = _this.loadingCtrl.create({
                            content: 'Suppression...',
                        });
                        _this.loading.present();
                        // this.operationAvecDepense.depense.remove();
                        var newDepense = _this.restangular.copy(_this.operationAvecDepense.depense);
                        newDepense.route = 'depense';
                        newDepense.remove().toPromise().then(function (resp) {
                            _this.loading.dismissAll();
                            console.log("dépense supprimée");
                            _this.operationAvecDepense.depense = null;
                            _this.event = null;
                        }, function (errorResponse) {
                            _this.constante.traiteErreur(errorResponse, _this);
                        });
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    return DetailOperation;
}());
DetailOperation = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'detail-operation',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-operation\cmy-detail-operation.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle >\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Operation {{operationAvecDepense.operation.description}}</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content>\n  <ion-grid>\n    <ion-row>\n      <ion-col col-4>Description :</ion-col>\n      <ion-col col-8>{{operationAvecDepense.operation.description}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Date :</ion-col>\n      <ion-col col-8>{{operationAvecDepense.operation.date}}</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Montant :</ion-col>\n      <ion-col col-8>{{operationAvecDepense.operation.montant.toFixed(2)}} €</ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Type :</ion-col>\n      <ion-col col-8><ion-chip color="secondary" class="toto"> {{operationAvecDepense.operation.typeOperation.libelle}}</ion-chip></ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Iban Emetteur :</ion-col>\n      <ion-col col-6>{{operationAvecDepense.operation.ibanEmetteur}}</ion-col>\n      <ion-col col-2 *ngIf="operationAvecDepense.operation.urlPhotoEmetteur"><preload-image style="border-radius: 50%" class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(operationAvecDepense.operation.urlPhotoEmetteur)\'></preload-image></ion-col>\n    </ion-row>\n    <ion-row>\n      <ion-col col-4>Iban Dest :</ion-col>\n      <ion-col col-6>{{operationAvecDepense.operation.ibanDestinataire}}</ion-col>\n      <ion-col col-2 *ngIf="operationAvecDepense.operation.urlPhotoDestinataire"><preload-image style="border-radius: 50%" class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(operationAvecDepense.operation.urlPhotoDestinataire)\'></preload-image></ion-col>\n    </ion-row>\n\n\n  </ion-grid>\n  <div  *ngIf="event!=null">\n    <p >Opération liée à l\'event {{ event.libelle}} du {{ event.date}} </p>\n    <button ion-button color="danger" (tap)="supprimeLien()">Supprimer la dépense</button>\n\n\n  </div>\n\n  <p *ngIf="event==null">Opération non liée à un event</p>\n\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-detail-operation\cmy-detail-operation.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["k" /* NavParams */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]])
], DetailOperation);

//# sourceMappingURL=cmy-detail-operation.js.map

/***/ }),

/***/ 418:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PageTest; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_liste_user__ = __webpack_require__(419);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__ionic_native_app_version__ = __webpack_require__(420);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__ionic_native_file_transfer__ = __webpack_require__(207);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__ionic_native_file__ = __webpack_require__(421);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__ionic_native_file_chooser__ = __webpack_require__(208);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__event_service__ = __webpack_require__(911);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};










var PageTest = (function () {
    function PageTest(eventService, fileChooser, transfer, file, angularEvents, appVersion, nav, constante, loadingCtrl, restangular, toastCtrl) {
        this.eventService = eventService;
        this.fileChooser = fileChooser;
        this.transfer = transfer;
        this.file = file;
        this.angularEvents = angularEvents;
        this.appVersion = appVersion;
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.toastCtrl = toastCtrl;
        this.etatIndex = "";
        this.user = this.constante.user;
    }
    ;
    PageTest.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.getVersion();
        this.restangular.one("utilitaire/rechercheIndexManquant").get().toPromise().then(function (rep) {
            var msg = "Recherche Index ";
            msg += rep.message;
            _this.etatIndex = rep.message;
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    PageTest.prototype.resetData = function () {
        var _this = this;
        this.loading = this.loadingCtrl.create();
        this.tpsReponseTraitement = 0;
        this.tpsReponseCouchbase = 0;
        this.tpsReponseServeur = 0;
        this.loading.present();
        this.restangular.one("utilitaire/initialisation").get().toPromise().then(function (rep) {
            _this.loading.dismissAll();
            var toast = _this.toastCtrl.create({
                message: "Données réinitialisées!",
                duration: 3000,
                position: 'top'
            });
            toast.present();
            return;
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    PageTest.prototype.getVersion = function () {
        var _this = this;
        this.appVersion.getVersionCode().then(function (rep) {
            _this.version1 = rep;
        }, function (error) {
            _this.version1 = "--";
            // console.log(error);
        });
        this.appVersion.getVersionNumber().then(function (rep) {
            _this.version2 = rep;
        }, function (error) {
            _this.version2 = "--";
        });
    };
    PageTest.prototype.checkInfra = function () {
        var _this = this;
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        this.etatServeur = "thumbs-down";
        this.etatDatabase = "thumbs-down";
        this.colorServeur = "danger";
        this.colorDatabase = "danger";
        var dateAppel = new Date();
        this.restangular.one("utilitaire/checkReseau").get().toPromise().then(function (rep) {
            _this.loading.dismissAll();
            _this.etatServeur = "thumbs-up";
            _this.colorServeur = "secondary";
            var timeFinServeur = rep.message.split(":")[1];
            _this.tpsReponseServeur = new Date().getTime() - dateAppel.getTime();
            console.log("Appel : " + dateAppel.getTime());
        }, function (error) {
            _this.etatServeur = "thumbs-down";
            _this.colorServeur = "danger";
            _this.constante.traiteErreur(error, _this);
        });
        this.restangular.one("utilitaire/checkDatabase").get().toPromise().then(function (rep) {
            _this.loading.dismissAll();
            _this.etatDatabase = "thumbs-up";
            _this.colorDatabase = "secondary";
            var timeServeur = rep.message.split(":")[1];
            var timeFin = rep.message.split(":")[2];
            _this.tpsReponseCouchbase = timeFin - timeServeur;
            console.log("Appel : " + dateAppel.getTime());
        }, function (error) {
            _this.etatDatabase = "thumbs-down";
            _this.colorDatabase = "danger";
            _this.constante.traiteErreur(error, _this);
        });
        this.restangular.one("utilitaire/checkPerformance").get().toPromise().then(function (rep) {
            _this.loading.dismissAll();
            _this.etatPerformance = "thumbs-up";
            _this.colorPerformance = "secondary";
            var timeFin = rep.stop;
            var timeDebut = rep.start;
            _this.tpsReponseTraitement = timeFin - timeDebut;
            if (_this.tpsReponseTraitement > 1000) {
                _this.etatPerformance = "thumbs-down";
                _this.colorPerformance = "danger";
            }
        }, function (error) {
            _this.etatPerformance = "thumbs-down";
            _this.colorPerformance = "danger";
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    PageTest.prototype.purgeServeur = function () {
        var _this = this;
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        this.restangular.one("utilitaire/purgeServeur").get().toPromise().then(function (rep) {
            _this.loading.dismissAll();
            var toast = _this.toastCtrl.create({
                message: "Données réinitialisées : " + rep.message + "!",
                duration: 3000,
                position: 'top'
            });
            toast.present();
            return;
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    PageTest.prototype.alerteur = function () {
        this.constante.presentToast("Appui long pour déclencher!");
    };
    ;
    PageTest.prototype.swapUser = function () {
        var _this = this;
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_liste_user__["a" /* ListeUser */]);
        this.angularEvents.subscribe("swapUser", function (resp) {
            console.log("SWAP TO : " + resp);
            _this.user = resp;
        });
    };
    ;
    PageTest.prototype.toogleRecuperationIndex = function () {
        var _this = this;
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        this.restangular.one("utilitaire/toggleRechercheIndexManquant").get().toPromise().then(function (rep) {
            _this.loading.dismissAll();
            var msg = "Recherche Index ";
            msg += rep.message;
            _this.etatIndex = rep.message;
            var toast = _this.toastCtrl.create({
                message: msg,
                duration: 3000,
                position: 'top'
            });
            toast.present();
            return;
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    PageTest.prototype.showRecuperationIndex = function () {
        var _this = this;
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        this.restangular.one("utilitaire/indexManquant").get().toPromise().then(function (rep) {
            _this.loading.dismissAll();
            console.log(rep);
            return;
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    PageTest.prototype.testService = function () {
        this.loading = this.loadingCtrl.create();
        this.loading.present();
        var user = this.constante.user;
        var _env = this;
        this.eventService.getEvents(user)
            .then(function (events) {
            _env.loading.dismissAll();
            console.log(events);
        }, function (error) {
            _env.loading.dismissAll();
            console.log(error);
        });
    };
    ;
    return PageTest;
}());
PageTest = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'cmy-page-test',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-page-test\cmy-page-test.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Développeur Only...</ion-title>\n  </ion-navbar>\n</ion-header>\n\n\n<ion-content class="home">\n\n  <p class="intro-text">\n    CoMoneyTy - Version : {{version1}} - {{version2}}\n  </p>\n  <section padding>\n    <button padding ion-button block class="form-action-button create-post-button"  (tap)="alerteur()" (press)="resetData()" >Reset des datas!</button>\n  </section>\n\n  <section padding>\n    <button padding  ion-button block class="form-action-button create-post-button"   (tap)="alerteur()" (press)="checkInfra()">Check infrastructure</button>\n    <ion-item padding-top>\n      <ion-label item-start>Reseau : </ion-label>\n      <ion-icon item-left [color]="colorServeur" [name]="etatServeur"></ion-icon>\n      <ion-label item-end> Tps : {{tpsReponseServeur}} ms</ion-label>\n    </ion-item>\n    <ion-item padding-top>\n      <ion-label item-left>Database : </ion-label>\n      <ion-icon item-left [color]="colorDatabase" [name]="etatDatabase"></ion-icon>\n      <ion-label item-right> Tps : {{tpsReponseCouchbase}} ms</ion-label>\n    </ion-item>\n    <ion-item padding-top>\n      <ion-label item-left>Performance : </ion-label>\n      <ion-icon item-left [color]="colorPerformance" [name]="etatPerformance"></ion-icon>\n      <ion-label item-right> Tps : {{tpsReponseTraitement}} ms</ion-label>\n    </ion-item>\n  </section>\n\n  <section padding>\n    <button  padding ion-button block class="form-action-button create-post-button"  (tap)="alerteur();" (press)="purgeServeur()" >Test Stateless</button>\n  </section>\n  <section padding>\n    <button  padding ion-button block class="form-action-button create-post-button" type="button"   (tap)="alerteur()" (press)="swapUser()" >Swap User ({{user.prenom}})</button>\n  </section>\n  <section padding>\n    <button  padding ion-button block class="form-action-button create-post-button" type="button"   (tap)="toogleRecuperationIndex()" (press)="showRecuperationIndex()" >Swap Indexes Manquants {{etatIndex}}</button>\n  </section>\n  <section padding>\n    <button  padding ion-button block class="form-action-button create-post-button" type="button"   (tap)="alerteur()" (press)="testService()" >Test Services</button>\n  </section>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-page-test\cmy-page-test.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_9__event_service__["a" /* EventService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_9__event_service__["a" /* EventService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_8__ionic_native_file_chooser__["a" /* FileChooser */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_8__ionic_native_file_chooser__["a" /* FileChooser */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_6__ionic_native_file_transfer__["a" /* FileTransfer */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__ionic_native_file_transfer__["a" /* FileTransfer */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_7__ionic_native_file__["a" /* File */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__ionic_native_file__["a" /* File */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["c" /* Events */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["c" /* Events */]) === "function" && _e || Object, typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_5__ionic_native_app_version__["a" /* AppVersion */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__ionic_native_app_version__["a" /* AppVersion */]) === "function" && _f || Object, typeof (_g = typeof __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* NavController */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* NavController */]) === "function" && _g || Object, typeof (_h = typeof __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */]) === "function" && _h || Object, typeof (_j = typeof __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["f" /* LoadingController */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["f" /* LoadingController */]) === "function" && _j || Object, typeof (_k = typeof __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"]) === "function" && _k || Object, typeof (_l = typeof __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["n" /* ToastController */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["n" /* ToastController */]) === "function" && _l || Object])
], PageTest);

var _a, _b, _c, _d, _e, _f, _g, _h, _j, _k, _l;
//# sourceMappingURL=cmy-page-test.js.map

/***/ }),

/***/ 419:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeUser; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




//import 'rxjs/Rx';
var ListeUser = (function () {
    function ListeUser(angularEvents, nav, constante, loadingCtrl, restangular, toastCtrl) {
        this.angularEvents = angularEvents;
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.toastCtrl = toastCtrl;
    }
    ListeUser.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.restangular.all('user').getList().subscribe(function (users) {
            _this.users = users;
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ListeUser.prototype.swapUser = function (user) {
        var _this = this;
        localStorage.removeItem('id_token');
        localStorage.removeItem('user');
        user.password = "BRUTEFORCE!!!";
        this.restangular.one("user").post("login", user).subscribe(function (resp) {
            localStorage.setItem('id_token', resp.id);
            localStorage.setItem('user', JSON.stringify(resp.user));
            _this.constante.user = resp.user;
            _this.constante.presentToastAvecPosition("Nouvel utilisateur : " + user.prenom, "bottom");
            _this.angularEvents.publish("swapUser", resp.user);
            _this.nav.pop();
        }, function (errorResponse) {
            console.log("Error with status code", errorResponse.status);
        });
    };
    return ListeUser;
}());
ListeUser = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'cmy-liste-user',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-page-test\cmy-liste-user.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Choisir un user</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <ion-list >\n    <button  (press)="swapUser(user)" ion-item *ngFor="let user of users">\n      <ion-row align-items-center justifiy-content-end no-padding >\n        <ion-col no-padding col-3 class="item-avatar" >\n          <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(user.urlAvatar)\'></preload-image>\n        </ion-col>\n        <ion-col class="libelle" col-9>\n          <h3>{{user.nom}} {{user.prenom}}</h3>\n          <p><span *ngIf="user.phone">{{user.phone}}</span> - <span *ngIf="user.email">{{user.email}}</span></p>\n        </ion-col>\n      </ion-row>\n    </button>\n  </ion-list>\n\n\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-page-test\cmy-liste-user.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["c" /* Events */], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["n" /* ToastController */]])
], ListeUser);

//# sourceMappingURL=cmy-liste-user.js.map

/***/ }),

/***/ 422:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ClavierVirtuel; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_home_cmy_home__ = __webpack_require__(82);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__walkthrough_walkthrough__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_crypto_js__ = __webpack_require__(209);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_crypto_js___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_crypto_js__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var ClavierVirtuel = (function () {
    function ClavierVirtuel(nav, constante, loadingCtrl, restangular) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
    }
    ;
    ClavierVirtuel.prototype.ionViewDidLoad = function () {
        this.codeReel = localStorage.getItem("codecourt");
        this.codeSaisie = "";
        this.codeSaisieEtoile = "";
        this.nbEssai = 3;
    };
    ;
    ClavierVirtuel.prototype.connexionClassique = function () {
        this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_5__walkthrough_walkthrough__["a" /* WalkthroughPage */]);
    };
    ;
    ClavierVirtuel.prototype.saisie = function (nombre) {
        var _this = this;
        this.codeSaisie = this.codeSaisie + nombre;
        this.codeSaisieEtoile += "*";
        if (this.codeSaisie.length == 4) {
            var user = JSON.parse(localStorage.getItem('user'));
            var codesaisieCrypte = __WEBPACK_IMPORTED_MODULE_6_crypto_js___default.a.PBKDF2(this.codeSaisie, "AlwaysTheSameSalt", { keySize: 512 / 32, iterations: 5 }).toString();
            // Contrôle du code
            if (codesaisieCrypte == this.codeReel) {
                var user_1 = JSON.parse(localStorage.getItem("user"));
                this.restangular.one("user").post("loginCourt", user_1).subscribe(function (resp) {
                    localStorage.setItem('id_token', resp.id);
                    localStorage.setItem('user', JSON.stringify(resp.user));
                    // let hash = CryptoJS.SHA256("ddd").toString(CryptoJS.enc.Hex);;
                    if (resp.user.codecourt != null && resp.user.codecourt.length > 0) {
                        var derivedKey = __WEBPACK_IMPORTED_MODULE_6_crypto_js___default.a.PBKDF2(resp.user.codecourt, "AlwaysTheSameSalt", {
                            keySize: 512 / 32,
                            iterations: 5
                        }).toString();
                        //     let derivedKey = bcryptjs.hashSync(resp.user.codecourt, resp.user.id);
                        localStorage.setItem("codecourt", derivedKey);
                    }
                    _this.constante.login(resp.user);
                    _this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_4__cmy_home_cmy_home__["a" /* Home */]);
                }, function (errorResponse) {
                    _this.traiteErreur();
                    _this.constante.traiteErreur(errorResponse, _this);
                });
            }
            else {
                this.constante.presentToast("Le code saisi est incorrect!!!");
                this.traiteErreur();
            }
        }
    };
    ;
    ClavierVirtuel.prototype.traiteErreur = function () {
        this.codeSaisieEtoile = "";
        this.codeSaisie = "";
        this.nbEssai--;
        if (this.nbEssai == 1) {
            this.constante.presentToast("Attention plus que un essai!!!");
        }
        ;
        if (this.nbEssai <= 0) {
            this.constante.presentToast("Code verouillé!!");
            localStorage.removeItem("codecourt");
            this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_5__walkthrough_walkthrough__["a" /* WalkthroughPage */]);
        }
        ;
    };
    return ClavierVirtuel;
}());
ClavierVirtuel = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'clavier-virtuel',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-clavier-virtuel\cmy-clavier-virtuel.html"*/'<div class="page-wrap">\n  <section padding>\n  <ion-label style="text-align:center">Entrez votre code secret : {{codeSaisieEtoile}}</ion-label>\n  <div class="keypad">\n    <span (tap)="saisie(1)" >1</span>\n    <span (tap)="saisie(2)">2</span>\n    <span (tap)="saisie(3)">3</span>\n    <br>\n    <span (tap)="saisie(4)">4</span>\n    <span (tap)="saisie(5)">5</span>\n    <span (tap)="saisie(6)">6</span>\n    <br>\n    <span (tap)="saisie(7)">7</span>\n    <span (tap)="saisie(8)">8</span>\n    <span (tap)="saisie(9)">9</span>\n    <br>\n    <span class="star">*</span>\n    <span (tap)="saisie(0)">0</span>\n    <span class="hash">#</span>\n    <br>\n  </div>\n  </section>\n  <section padding>\n    <button padding ion-button block class="form-action-button create-post-button" (tap)="connexionClassique()">Connexion classique</button>\n  </section>\n\n</div>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-clavier-virtuel\cmy-clavier-virtuel.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"]])
], ClavierVirtuel);

//# sourceMappingURL=cmy-clavier-virtuel.js.map

/***/ }),

/***/ 424:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ShowHideInput; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var ShowHideInput = (function () {
    function ShowHideInput(el) {
        this.el = el;
        this.type = 'password';
    }
    ShowHideInput.prototype.changeType = function (type) {
        this.type = type;
        this.el.nativeElement.children[0].type = this.type;
    };
    return ShowHideInput;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["HostBinding"])(),
    __metadata("design:type", String)
], ShowHideInput.prototype, "type", void 0);
ShowHideInput = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Directive"])({
        selector: '[show-hide-input]'
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"]])
], ShowHideInput);

//# sourceMappingURL=show-hide-input.js.map

/***/ }),

/***/ 425:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(426);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__(430);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_core__ = __webpack_require__(0);



Object(__WEBPACK_IMPORTED_MODULE_2__angular_core__["enableProdMode"])();
Object(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_1__app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 430:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export RestangularConfigFactory */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_component__ = __webpack_require__(467);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_contacts__ = __webpack_require__(410);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_cmy_liste_event_cmy_liste_event__ = __webpack_require__(71);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_cmy_liste_operation_cmy_liste_operation__ = __webpack_require__(416);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_cmy_creation_event_cmy_creation_event__ = __webpack_require__(403);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_cmy_creation_depense_cmy_creation_depense__ = __webpack_require__(395);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_cmy_detail_event_cmy_detail_event__ = __webpack_require__(319);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__pages_cmy_ajout_participant_cmy_ajout_participant__ = __webpack_require__(320);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_cmy_liste_depense_cmy_liste_depense__ = __webpack_require__(397);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__pages_cmy_modal_modal_photo__ = __webpack_require__(205);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_cmy_modal_modal_choix_event__ = __webpack_require__(216);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__pages_cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__pages_login_login__ = __webpack_require__(173);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__pages_signup_signup__ = __webpack_require__(174);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__pages_forgot_password_forgot_password__ = __webpack_require__(414);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__components_preload_image_preload_image__ = __webpack_require__(912);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__components_background_image_background_image__ = __webpack_require__(913);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__components_show_hide_password_show_hide_container__ = __webpack_require__(914);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__components_show_hide_password_show_hide_input__ = __webpack_require__(424);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21__components_color_radio_color_radio__ = __webpack_require__(915);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22__pages_walkthrough_walkthrough__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__angular_platform_browser__ = __webpack_require__(51);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__angular_http__ = __webpack_require__(58);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25__ionic_native_splash_screen__ = __webpack_require__(314);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26__ionic_native_status_bar__ = __webpack_require__(318);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27__ionic_native_native_storage__ = __webpack_require__(213);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_28__ionic_native_in_app_browser__ = __webpack_require__(916);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_29__ionic_native_facebook__ = __webpack_require__(407);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_30__ionic_native_google_plus__ = __webpack_require__(408);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_31__ionic_native_keyboard__ = __webpack_require__(917);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_32__ionic_native_camera__ = __webpack_require__(81);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_33__pages_login_facebook_login_service__ = __webpack_require__(212);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_34__pages_login_google_login_service__ = __webpack_require__(214);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_35_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_35_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_35_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_36__pages_cmy_detail_operation_cmy_detail_operation__ = __webpack_require__(417);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_37__pages_cmy_modal_modal_choix_operation__ = __webpack_require__(396);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_38__ionic_native_sms__ = __webpack_require__(72);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_39__pages_cmy_gestion_ami_cmy_gestion_ami__ = __webpack_require__(215);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_40__pages_cmy_invitation_ami_cmy_invitation_ami__ = __webpack_require__(409);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_41__pages_cmy_list_message_cmy_liste_message__ = __webpack_require__(217);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_42__pages_cmy_gestion_profile_cmy_gestion_profile__ = __webpack_require__(125);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_43__pages_cmy_bilan_event_cmy_bilan_event__ = __webpack_require__(402);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_44__pages_cmy_detail_message_cmy_detail_message__ = __webpack_require__(412);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_45__pages_cmy_page_test_cmy_page_test__ = __webpack_require__(418);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_46__components_menu_circular_menu_circular__ = __webpack_require__(124);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_47__pages_cmy_liste_ordre_cmy_liste_ordre__ = __webpack_require__(918);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_48__pages_cmy_paiement_ordre_cmy_paiement_ordre__ = __webpack_require__(218);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_49__pages_cmy_detail_ordre_cmy_detail_ordre__ = __webpack_require__(219);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_50__ionic_native_paypal__ = __webpack_require__(413);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_51__pages_privacy_policy_privacy_policy__ = __webpack_require__(220);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_52__pages_cmy_page_test_cmy_liste_user__ = __webpack_require__(419);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_53__pages_cmy_liste_historique_cmy_liste_historique__ = __webpack_require__(404);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_54__ionic_native_app_version__ = __webpack_require__(420);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_55__pages_cmy_home_cmy_home__ = __webpack_require__(82);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_56__pages_cmy_clavier_virtuel_cmy_clavier_virtuel__ = __webpack_require__(422);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_57__ionic_native_badge__ = __webpack_require__(423);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_58__pages_cmy_detail_ami_cmy_detail_ami__ = __webpack_require__(411);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_59__pages_master_master_home__ = __webpack_require__(415);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_60__pages_cmy_liste_document_cmy_liste_document__ = __webpack_require__(206);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_61__pages_cmy_ajout_document_cmy_ajout_document__ = __webpack_require__(398);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_62__pages_cmy_modal_modal_one_photo__ = __webpack_require__(400);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_63__ionic_native_file_transfer__ = __webpack_require__(207);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_64__ionic_native_file__ = __webpack_require__(421);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_65__ionic_native_file_chooser__ = __webpack_require__(208);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_66__ionic_native_file_opener__ = __webpack_require__(401);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_67__ionic_native_file_path__ = __webpack_require__(399);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_68__pages_cmy_page_test_event_service__ = __webpack_require__(911);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





































































// Functionalities
// Function for setting the default restangular configuration
function RestangularConfigFactory(RestangularProvider) {
    RestangularProvider.setBaseUrl('http://vulcanjibe.ddns.net:8080/CoMoneyTy-0.0.1-SNAPSHOT/rest');
    // set static header
    RestangularProvider.setDefaultHeaders({ 'Authorization': 'Bearer UDXPx-Xko0w4BRKajozCVy20X11MRZs1' });
    // by each request to the server receive a token and update headers with it
    RestangularProvider.addFullRequestInterceptor(function (element, operation, path, url, headers, params) {
        var bearerToken = localStorage.getItem('id_token');
        return {
            headers: Object.assign({}, headers, { Authorization: "TOKEN=" + bearerToken })
        };
    });
}
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* MyApp */],
            __WEBPACK_IMPORTED_MODULE_55__pages_cmy_home_cmy_home__["a" /* Home */],
            __WEBPACK_IMPORTED_MODULE_60__pages_cmy_liste_document_cmy_liste_document__["a" /* ListeDocument */],
            __WEBPACK_IMPORTED_MODULE_61__pages_cmy_ajout_document_cmy_ajout_document__["a" /* AjoutDocumentPage */],
            __WEBPACK_IMPORTED_MODULE_56__pages_cmy_clavier_virtuel_cmy_clavier_virtuel__["a" /* ClavierVirtuel */],
            __WEBPACK_IMPORTED_MODULE_58__pages_cmy_detail_ami_cmy_detail_ami__["a" /* DetailAmi */],
            __WEBPACK_IMPORTED_MODULE_8__pages_cmy_detail_event_cmy_detail_event__["a" /* DetailEventPage */],
            __WEBPACK_IMPORTED_MODULE_51__pages_privacy_policy_privacy_policy__["a" /* PrivacyPolicyPage */],
            __WEBPACK_IMPORTED_MODULE_43__pages_cmy_bilan_event_cmy_bilan_event__["a" /* BilanEvent */],
            __WEBPACK_IMPORTED_MODULE_42__pages_cmy_gestion_profile_cmy_gestion_profile__["a" /* GestionProfile */],
            __WEBPACK_IMPORTED_MODULE_7__pages_cmy_creation_depense_cmy_creation_depense__["a" /* CreationDepensePage */],
            __WEBPACK_IMPORTED_MODULE_45__pages_cmy_page_test_cmy_page_test__["a" /* PageTest */],
            __WEBPACK_IMPORTED_MODULE_36__pages_cmy_detail_operation_cmy_detail_operation__["a" /* DetailOperation */],
            __WEBPACK_IMPORTED_MODULE_10__pages_cmy_liste_depense_cmy_liste_depense__["a" /* ListeDepense */],
            __WEBPACK_IMPORTED_MODULE_47__pages_cmy_liste_ordre_cmy_liste_ordre__["a" /* ListeOrdre */],
            __WEBPACK_IMPORTED_MODULE_39__pages_cmy_gestion_ami_cmy_gestion_ami__["a" /* GestionAmi */],
            __WEBPACK_IMPORTED_MODULE_40__pages_cmy_invitation_ami_cmy_invitation_ami__["a" /* InvitationAmi */],
            __WEBPACK_IMPORTED_MODULE_41__pages_cmy_list_message_cmy_liste_message__["a" /* ListeMessage */],
            __WEBPACK_IMPORTED_MODULE_9__pages_cmy_ajout_participant_cmy_ajout_participant__["a" /* AjoutParticipantPage */],
            __WEBPACK_IMPORTED_MODULE_11__pages_cmy_modal_modal_photo__["a" /* ModalPhoto */],
            __WEBPACK_IMPORTED_MODULE_62__pages_cmy_modal_modal_one_photo__["a" /* ModalOnePhoto */],
            __WEBPACK_IMPORTED_MODULE_52__pages_cmy_page_test_cmy_liste_user__["a" /* ListeUser */],
            __WEBPACK_IMPORTED_MODULE_12__pages_cmy_modal_modal_choix_event__["a" /* ModalChoixEvent */],
            __WEBPACK_IMPORTED_MODULE_48__pages_cmy_paiement_ordre_cmy_paiement_ordre__["a" /* PaiementOrdre */],
            __WEBPACK_IMPORTED_MODULE_49__pages_cmy_detail_ordre_cmy_detail_ordre__["a" /* DetailOrdre */],
            __WEBPACK_IMPORTED_MODULE_44__pages_cmy_detail_message_cmy_detail_message__["a" /* DetailMessage */],
            __WEBPACK_IMPORTED_MODULE_37__pages_cmy_modal_modal_choix_operation__["a" /* ModalChoixOperation */],
            __WEBPACK_IMPORTED_MODULE_6__pages_cmy_creation_event_cmy_creation_event__["a" /* CreationEventPage */],
            __WEBPACK_IMPORTED_MODULE_53__pages_cmy_liste_historique_cmy_liste_historique__["a" /* ListeHistorique */],
            __WEBPACK_IMPORTED_MODULE_4__pages_cmy_liste_event_cmy_liste_event__["a" /* ListeEvent */],
            __WEBPACK_IMPORTED_MODULE_5__pages_cmy_liste_operation_cmy_liste_operation__["a" /* ListeOperation */],
            __WEBPACK_IMPORTED_MODULE_14__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_15__pages_signup_signup__["a" /* SignupPage */],
            __WEBPACK_IMPORTED_MODULE_59__pages_master_master_home__["a" /* MasterHome */],
            __WEBPACK_IMPORTED_MODULE_16__pages_forgot_password_forgot_password__["a" /* ForgotPasswordPage */],
            __WEBPACK_IMPORTED_MODULE_22__pages_walkthrough_walkthrough__["a" /* WalkthroughPage */],
            __WEBPACK_IMPORTED_MODULE_17__components_preload_image_preload_image__["a" /* PreloadImage */],
            __WEBPACK_IMPORTED_MODULE_46__components_menu_circular_menu_circular__["a" /* MenuCircular */],
            __WEBPACK_IMPORTED_MODULE_18__components_background_image_background_image__["a" /* BackgroundImage */],
            __WEBPACK_IMPORTED_MODULE_19__components_show_hide_password_show_hide_container__["a" /* ShowHideContainer */],
            __WEBPACK_IMPORTED_MODULE_20__components_show_hide_password_show_hide_input__["a" /* ShowHideInput */],
            __WEBPACK_IMPORTED_MODULE_21__components_color_radio_color_radio__["a" /* ColorRadio */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_23__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_24__angular_http__["HttpModule"],
            __WEBPACK_IMPORTED_MODULE_35_ngx_restangular__["RestangularModule"].forRoot(RestangularConfigFactory),
            __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["e" /* IonicModule */].forRoot(__WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* MyApp */], {}, {
                links: []
            })
        ],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["d" /* IonicApp */]],
        entryComponents: [
            __WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* MyApp */],
            __WEBPACK_IMPORTED_MODULE_55__pages_cmy_home_cmy_home__["a" /* Home */],
            __WEBPACK_IMPORTED_MODULE_60__pages_cmy_liste_document_cmy_liste_document__["a" /* ListeDocument */],
            __WEBPACK_IMPORTED_MODULE_61__pages_cmy_ajout_document_cmy_ajout_document__["a" /* AjoutDocumentPage */],
            __WEBPACK_IMPORTED_MODULE_56__pages_cmy_clavier_virtuel_cmy_clavier_virtuel__["a" /* ClavierVirtuel */],
            __WEBPACK_IMPORTED_MODULE_42__pages_cmy_gestion_profile_cmy_gestion_profile__["a" /* GestionProfile */],
            __WEBPACK_IMPORTED_MODULE_52__pages_cmy_page_test_cmy_liste_user__["a" /* ListeUser */],
            __WEBPACK_IMPORTED_MODULE_45__pages_cmy_page_test_cmy_page_test__["a" /* PageTest */],
            __WEBPACK_IMPORTED_MODULE_51__pages_privacy_policy_privacy_policy__["a" /* PrivacyPolicyPage */],
            __WEBPACK_IMPORTED_MODULE_41__pages_cmy_list_message_cmy_liste_message__["a" /* ListeMessage */],
            __WEBPACK_IMPORTED_MODULE_47__pages_cmy_liste_ordre_cmy_liste_ordre__["a" /* ListeOrdre */],
            __WEBPACK_IMPORTED_MODULE_48__pages_cmy_paiement_ordre_cmy_paiement_ordre__["a" /* PaiementOrdre */],
            __WEBPACK_IMPORTED_MODULE_49__pages_cmy_detail_ordre_cmy_detail_ordre__["a" /* DetailOrdre */],
            __WEBPACK_IMPORTED_MODULE_43__pages_cmy_bilan_event_cmy_bilan_event__["a" /* BilanEvent */],
            __WEBPACK_IMPORTED_MODULE_44__pages_cmy_detail_message_cmy_detail_message__["a" /* DetailMessage */],
            __WEBPACK_IMPORTED_MODULE_4__pages_cmy_liste_event_cmy_liste_event__["a" /* ListeEvent */],
            __WEBPACK_IMPORTED_MODULE_53__pages_cmy_liste_historique_cmy_liste_historique__["a" /* ListeHistorique */],
            __WEBPACK_IMPORTED_MODULE_36__pages_cmy_detail_operation_cmy_detail_operation__["a" /* DetailOperation */],
            __WEBPACK_IMPORTED_MODULE_10__pages_cmy_liste_depense_cmy_liste_depense__["a" /* ListeDepense */],
            __WEBPACK_IMPORTED_MODULE_39__pages_cmy_gestion_ami_cmy_gestion_ami__["a" /* GestionAmi */],
            __WEBPACK_IMPORTED_MODULE_5__pages_cmy_liste_operation_cmy_liste_operation__["a" /* ListeOperation */],
            __WEBPACK_IMPORTED_MODULE_41__pages_cmy_list_message_cmy_liste_message__["a" /* ListeMessage */],
            __WEBPACK_IMPORTED_MODULE_6__pages_cmy_creation_event_cmy_creation_event__["a" /* CreationEventPage */],
            __WEBPACK_IMPORTED_MODULE_7__pages_cmy_creation_depense_cmy_creation_depense__["a" /* CreationDepensePage */],
            __WEBPACK_IMPORTED_MODULE_8__pages_cmy_detail_event_cmy_detail_event__["a" /* DetailEventPage */],
            __WEBPACK_IMPORTED_MODULE_40__pages_cmy_invitation_ami_cmy_invitation_ami__["a" /* InvitationAmi */],
            __WEBPACK_IMPORTED_MODULE_9__pages_cmy_ajout_participant_cmy_ajout_participant__["a" /* AjoutParticipantPage */],
            __WEBPACK_IMPORTED_MODULE_11__pages_cmy_modal_modal_photo__["a" /* ModalPhoto */],
            __WEBPACK_IMPORTED_MODULE_62__pages_cmy_modal_modal_one_photo__["a" /* ModalOnePhoto */],
            __WEBPACK_IMPORTED_MODULE_12__pages_cmy_modal_modal_choix_event__["a" /* ModalChoixEvent */],
            __WEBPACK_IMPORTED_MODULE_37__pages_cmy_modal_modal_choix_operation__["a" /* ModalChoixOperation */],
            __WEBPACK_IMPORTED_MODULE_14__pages_login_login__["a" /* LoginPage */],
            __WEBPACK_IMPORTED_MODULE_58__pages_cmy_detail_ami_cmy_detail_ami__["a" /* DetailAmi */],
            __WEBPACK_IMPORTED_MODULE_59__pages_master_master_home__["a" /* MasterHome */],
            __WEBPACK_IMPORTED_MODULE_22__pages_walkthrough_walkthrough__["a" /* WalkthroughPage */],
            __WEBPACK_IMPORTED_MODULE_16__pages_forgot_password_forgot_password__["a" /* ForgotPasswordPage */],
            __WEBPACK_IMPORTED_MODULE_15__pages_signup_signup__["a" /* SignupPage */]
        ],
        providers: [
            __WEBPACK_IMPORTED_MODULE_38__ionic_native_sms__["a" /* SMS */],
            __WEBPACK_IMPORTED_MODULE_13__pages_cmy_model_cmy_model__["a" /* Constante */],
            __WEBPACK_IMPORTED_MODULE_54__ionic_native_app_version__["a" /* AppVersion */],
            __WEBPACK_IMPORTED_MODULE_57__ionic_native_badge__["a" /* Badge */],
            __WEBPACK_IMPORTED_MODULE_68__pages_cmy_page_test_event_service__["a" /* EventService */],
            __WEBPACK_IMPORTED_MODULE_33__pages_login_facebook_login_service__["a" /* FacebookLoginService */],
            __WEBPACK_IMPORTED_MODULE_34__pages_login_google_login_service__["a" /* GoogleLoginService */],
            __WEBPACK_IMPORTED_MODULE_32__ionic_native_camera__["a" /* Camera */],
            __WEBPACK_IMPORTED_MODULE_3__ionic_native_contacts__["b" /* Contacts */],
            __WEBPACK_IMPORTED_MODULE_50__ionic_native_paypal__["a" /* PayPal */],
            __WEBPACK_IMPORTED_MODULE_25__ionic_native_splash_screen__["a" /* SplashScreen */],
            __WEBPACK_IMPORTED_MODULE_26__ionic_native_status_bar__["a" /* StatusBar */],
            __WEBPACK_IMPORTED_MODULE_27__ionic_native_native_storage__["a" /* NativeStorage */],
            __WEBPACK_IMPORTED_MODULE_28__ionic_native_in_app_browser__["a" /* InAppBrowser */],
            __WEBPACK_IMPORTED_MODULE_29__ionic_native_facebook__["a" /* Facebook */],
            __WEBPACK_IMPORTED_MODULE_30__ionic_native_google_plus__["a" /* GooglePlus */],
            __WEBPACK_IMPORTED_MODULE_31__ionic_native_keyboard__["a" /* Keyboard */],
            __WEBPACK_IMPORTED_MODULE_63__ionic_native_file_transfer__["a" /* FileTransfer */],
            __WEBPACK_IMPORTED_MODULE_64__ionic_native_file__["a" /* File */],
            __WEBPACK_IMPORTED_MODULE_65__ionic_native_file_chooser__["a" /* FileChooser */],
            __WEBPACK_IMPORTED_MODULE_66__ionic_native_file_opener__["a" /* FileOpener */],
            __WEBPACK_IMPORTED_MODULE_67__ionic_native_file_path__["a" /* FilePath */]
        ],
        schemas: [__WEBPACK_IMPORTED_MODULE_0__angular_core__["CUSTOM_ELEMENTS_SCHEMA"]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 467:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MyApp; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ionic_native_splash_screen__ = __webpack_require__(314);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__ionic_native_status_bar__ = __webpack_require__(318);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__pages_walkthrough_walkthrough__ = __webpack_require__(100);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__pages_cmy_liste_event_cmy_liste_event__ = __webpack_require__(71);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__pages_cmy_liste_operation_cmy_liste_operation__ = __webpack_require__(416);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__pages_cmy_gestion_ami_cmy_gestion_ami__ = __webpack_require__(215);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__pages_cmy_list_message_cmy_liste_message__ = __webpack_require__(217);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__pages_cmy_gestion_profile_cmy_gestion_profile__ = __webpack_require__(125);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__pages_cmy_page_test_cmy_page_test__ = __webpack_require__(418);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_11_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__pages_cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__pages_cmy_home_cmy_home__ = __webpack_require__(82);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__pages_cmy_clavier_virtuel_cmy_clavier_virtuel__ = __webpack_require__(422);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__ionic_native_badge__ = __webpack_require__(423);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
















var MyApp = (function () {
    function MyApp(platform, menu, app, alertCtrl, splashScreen, statusBar, restangular, constante, badge) {
        var _this = this;
        this.platform = platform;
        this.menu = menu;
        this.app = app;
        this.alertCtrl = alertCtrl;
        this.splashScreen = splashScreen;
        this.statusBar = statusBar;
        this.restangular = restangular;
        this.constante = constante;
        this.badge = badge;
        // rootPage: any = TabsNavigationPage;
        this.background = false;
        this.nbMessage = 0;
        platform.pause.subscribe(function (rep) {
            _this.background = true;
            clearTimeout(_this.timer);
            var that = _this;
            _this.timer = setInterval(function () {
                that.refreshMessagerie();
            }, 600000);
        });
        platform.resume.subscribe(function (rep) {
            _this.background = false;
            clearTimeout(_this.timer);
            _this.refreshMessagerie();
            var that = _this;
            _this.timer = setInterval(function () {
                that.refreshMessagerie();
            }, 180000);
        });
        platform.ready().then(function () {
            // Okay, so the platform is ready and our plugins are available.
            // Here you can do any higher level native things you might need.
            _this.splashScreen.hide();
            _this.statusBar.styleDefault();
            var codecourt = localStorage.getItem("codecourt");
            if (codecourt != null && codecourt != "----" && codecourt != "") {
                _this.rootPage = __WEBPACK_IMPORTED_MODULE_14__pages_cmy_clavier_virtuel_cmy_clavier_virtuel__["a" /* ClavierVirtuel */];
            }
            else {
                _this.rootPage = __WEBPACK_IMPORTED_MODULE_4__pages_walkthrough_walkthrough__["a" /* WalkthroughPage */];
            }
            _this.ionicApp = _this.app._appRoot;
            _this.platform.registerBackButtonAction(function () {
                var activePortal = _this.ionicApp._loadingPortal.getActive() ||
                    _this.ionicApp._modalPortal.getActive() ||
                    _this.ionicApp._toastPortal.getActive() ||
                    _this.ionicApp._overlayPortal.getActive();
                if (activePortal) {
                    // ready = false;
                    activePortal.dismiss();
                    activePortal.onDidDismiss(function () { });
                    console.log("handled with portal");
                    return;
                }
                if (_this.nav.length() == 1)
                    _this.confirmExit();
                else
                    _this.nav.pop();
            });
        });
        this.constante.userChange.subscribe(function (event) {
            if (_this.constante.user == null)
                return;
            if (_this.constante.user.phone == null) {
                var alert_1 = _this.alertCtrl.create({
                    title: 'Votre profil est incomplet... Rajoutez votre numéro de téléphone pour rejoindre vos amis.',
                    message: "Voulez-vous compléter votre profil maintenant?",
                    buttons: [
                        {
                            text: 'Oui',
                            role: 'cancel',
                            handler: function () {
                                // Transofrmation de l'invit en relation
                                _this.app.getRootNav().push(__WEBPACK_IMPORTED_MODULE_9__pages_cmy_gestion_profile_cmy_gestion_profile__["a" /* GestionProfile */]);
                            }
                        },
                        {
                            text: 'Non',
                            handler: function () {
                            }
                        }
                    ]
                });
                alert_1.present();
            }
            console.log("user logged in!!!");
            // Lancement de la messagerie
            var that = _this;
            _this.refreshMessagerie();
            _this.timer = setInterval(function () {
                that.refreshMessagerie();
            }, 180000);
        });
        this.pages = [
            { title: 'Home', icon: 'home', component: __WEBPACK_IMPORTED_MODULE_13__pages_cmy_home_cmy_home__["a" /* Home */] },
            { title: 'Event', icon: 'people', component: __WEBPACK_IMPORTED_MODULE_5__pages_cmy_liste_event_cmy_liste_event__["a" /* ListeEvent */] },
            { title: 'Operation', icon: 'swap', component: __WEBPACK_IMPORTED_MODULE_6__pages_cmy_liste_operation_cmy_liste_operation__["a" /* ListeOperation */] },
            //  { title: 'Ordres', icon: 'cash', component: ListeOrdre },
            { title: 'Message', icon: 'mail', component: __WEBPACK_IMPORTED_MODULE_8__pages_cmy_list_message_cmy_liste_message__["a" /* ListeMessage */] },
            { title: 'Amis', icon: 'people', component: __WEBPACK_IMPORTED_MODULE_7__pages_cmy_gestion_ami_cmy_gestion_ami__["a" /* GestionAmi */] },
            { title: 'Dev Only!', icon: 'bug', component: __WEBPACK_IMPORTED_MODULE_10__pages_cmy_page_test_cmy_page_test__["a" /* PageTest */] }
        ];
        this.pushPages = [
            { title: 'Profile', icon: 'settings', component: __WEBPACK_IMPORTED_MODULE_9__pages_cmy_gestion_profile_cmy_gestion_profile__["a" /* GestionProfile */] }
        ];
    }
    ;
    MyApp.prototype.confirmExit = function () {
        var _this = this;
        var alert = this.alertCtrl.create({
            title: "Quitter CoMoneyTy?",
            message: "Etes-vous sûr de voir quitter CoMoneyTy?",
            buttons: [
                {
                    text: "Annuler",
                    handler: function () {
                        return;
                    }
                }, {
                    text: "OK",
                    handler: function () {
                        _this.platform.exitApp();
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    MyApp.prototype.refreshMessagerie = function () {
        var _this = this;
        ///console.log("Salut mec!");
        if (this.background)
            return;
        if (this.constante.user == null || this.constante.user.id == null) {
            console.log("Not logged");
            return;
        }
        this.restangular.one('user/' + this.constante.user.id + '/nbMessagesNonLu').get().subscribe(function (rep) {
            var newNb = rep.message.split("=")[1];
            if (_this.nbMessage != newNb) {
                _this.nbMessage = newNb;
                _this.badge.set(_this.nbMessage);
            }
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    MyApp.prototype.openPage = function (page) {
        // close the menu when (tap)ing a link from the menu
        this.menu.close();
        // navigate to the new page if it is not the current page
        this.nav.setRoot(page.component);
    };
    ;
    MyApp.prototype.pushPage = function (page) {
        // close the menu when (tap)ing a link from the menu
        this.menu.close();
        // rootNav is now deprecated (since beta 11) (https://forum.ionicframework.com/t/cant-access-rootnav-after-upgrade-to-beta-11/59889)
        this.app.getRootNav().push(page.component);
    };
    ;
    return MyApp;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])(__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["i" /* Nav */]),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["i" /* Nav */])
], MyApp.prototype, "nav", void 0);
MyApp = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-root',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\app\app.html"*/'<ion-menu [content]="content" [swipeEnabled]="false">\n  <ion-content class="menu-content">\n    <ion-list class="menu-list">\n      <button ion-item detail-none *ngFor="let page of pages" (tap)="openPage(page)">\n        <ion-icon *ngIf="page.icon" name="{{page.icon}}" item-left></ion-icon>\n        {{page.title}}\n        <ion-badge item-end *ngIf="page.title==\'Message\'">{{nbMessage}}</ion-badge>\n      </button>\n      <button ion-item detail-none *ngFor="let page of pushPages" (tap)="pushPage(page)">\n        <ion-icon *ngIf="page.icon" name="{{page.icon}}" item-left></ion-icon>\n        {{page.title}}\n      </button>\n    </ion-list>\n  </ion-content>\n</ion-menu>\n\n<ion-nav [root]="rootPage" #content swipe-back-enabled="false"></ion-nav>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\app\app.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["l" /* Platform */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["g" /* MenuController */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["b" /* App */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */],
        __WEBPACK_IMPORTED_MODULE_2__ionic_native_splash_screen__["a" /* SplashScreen */],
        __WEBPACK_IMPORTED_MODULE_3__ionic_native_status_bar__["a" /* StatusBar */], __WEBPACK_IMPORTED_MODULE_11_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_12__pages_cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_15__ionic_native_badge__["a" /* Badge */]])
], MyApp);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 71:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeEvent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_detail_event_cmy_detail_event__ = __webpack_require__(319);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_creation_event_cmy_creation_event__ = __webpack_require__(403);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__ = __webpack_require__(124);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__cmy_liste_historique_cmy_liste_historique__ = __webpack_require__(404);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__cmy_gestion_profile_cmy_gestion_profile__ = __webpack_require__(125);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};









//import 'rxjs/Rx';
var ListeEvent = (function () {
    function ListeEvent(nav, constante, loadingCtrl, alertCtrl, restangular, alertController) {
        var _this = this;
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.alertCtrl = alertCtrl;
        this.restangular = restangular;
        this.alertController = alertController;
        this.visible = false;
        this.loading = this.loadingCtrl.create();
        this.visible = false;
        this.filtreEtat = new Array();
        this.filtreEtat.push("Ouvert");
        this.filtreEtat.push("En cours de solde");
        this.constante.eventChange.subscribe(function (newEvent) {
            console.log("TouchEvent!");
            _this.calculResume(newEvent);
        });
    }
    ;
    ListeEvent.prototype.filtreType = function () {
        var _this = this;
        var alert = this.alertCtrl.create({
            cssClass: 'category-prompt'
        });
        alert.setTitle('Filtre par type');
        alert.addInput({
            type: 'checkbox',
            label: 'En cours',
            value: 'Ouvert',
            checked: this.filtreEtat.indexOf('Ouvert') > -1
        });
        alert.addInput({
            type: 'checkbox',
            label: 'En cours de solde',
            value: 'En cours de solde',
            checked: this.filtreEtat.indexOf('En cours de solde') > -1
        });
        alert.addInput({
            type: 'checkbox',
            label: 'Clos',
            value: 'Clos',
            checked: this.filtreEtat.indexOf('Clos') > -1
        });
        alert.addInput({
            type: 'checkbox',
            label: 'Annulé',
            value: 'Annulé',
            checked: this.filtreEtat.indexOf('Annulé') > -1
        });
        alert.addButton('Annule');
        alert.addButton({
            text: 'OK',
            handler: function (data) {
                console.log('Checkbox data:', data);
                _this.filtreEtat = data;
                _this.filtreEvent(_this.valeurFiltre);
            }
        });
        alert.present().then(function (res) {
            console.log(res);
        });
    };
    ;
    ListeEvent.prototype.calculResume = function (newEvent) {
        this.depenseTotale = 0;
        if (this.events == null)
            return;
        for (var _i = 0, _a = this.events; _i < _a.length; _i++) {
            var event_1 = _a[_i];
            if (newEvent != null && newEvent.id == event_1.id) {
                event_1.montantDu = newEvent.montantDu;
                event_1.montantDepense = newEvent.montantDepense;
                event_1.montantTotal = newEvent.montantTotal;
            }
            if (event_1.montantDu > 0)
                this.depenseTotale += event_1.montantDu;
        }
    };
    ListeEvent.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        console.log(this.constante.BASE_URL_REST);
        // This will query /accounts and return a observable.
        this.restangular.all('user/' + this.constante.user.id + '/events').getList().subscribe(function (events) {
            _this.events = events;
            _this.eventsComplet = events;
            _this.calculResume(null);
            _this.filtreEvent("");
            _this.loading.dismiss();
            if (_this.constante.user.phone == null || _this.constante.user.phone.length < 5) {
                if (events.log() == 0) {
                    var alert_1 = _this.alertController.create({
                        title: 'Votre profil est incomplet (Téléphone!)',
                        message: "Voulez-vous accéder directement à votre profil pour le compléter?",
                        buttons: [
                            {
                                text: 'Oui',
                                role: 'cancel',
                                handler: function () {
                                    _this.nav.push(__WEBPACK_IMPORTED_MODULE_8__cmy_gestion_profile_cmy_gestion_profile__["a" /* GestionProfile */]);
                                }
                            },
                            {
                                text: 'Non',
                                handler: function () {
                                }
                            }
                        ]
                    });
                    alert_1.present();
                }
            }
            ;
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
        var sousmenus = new Array();
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Fermer", this.close, "home"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Annuler", this.trash, "trash"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Copier", this.copy, "copy"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Log", this.log, "list"));
        sousmenus.push(new __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["b" /* SousMenu */]("Quitter", this.closeMenu, "close"));
        this.menu.config(sousmenus);
    };
    ListeEvent.prototype.createNewEvent = function () {
        console.log("Creation Event!");
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_creation_event_cmy_creation_event__["a" /* CreationEventPage */], { listeEvent: this.events });
    };
    ListeEvent.prototype.trash = function (eventSelectionne) {
        var _this = this;
        if (eventSelectionne.roles.indexOf("Createur") < 0) {
            this.constante.presentToast("Seul le créateur peut annuler un event!");
            return;
        }
        ;
        var alert = this.alertController.create({
            title: "Supprimer l'event?",
            message: "Confirmez-vous que vous voulez supprimer cet event?",
            buttons: [
                {
                    text: 'Oui',
                    role: 'cancel',
                    handler: function () {
                        // On bascule juste le mouvement en "effectué"
                        console.log("annulation de " + eventSelectionne.id);
                        var eventRest = _this.restangular.copy(eventSelectionne);
                        eventRest.route = "event";
                        eventRest.etat = "Annulé";
                        _this.loading = _this.loadingCtrl.create();
                        _this.loading.present();
                        eventRest.save().toPromise().then(function (resp) {
                            _this.loading.dismissAll();
                            eventSelectionne.etat = "Annulé";
                            _this.menu.close();
                        }, function (errorResponse) {
                            _this.constante.traiteErreur(errorResponse, _this);
                        });
                    }
                },
                {
                    text: 'Non',
                    handler: function () {
                        console.log("Abandon");
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    ListeEvent.prototype.log = function (eventSelectionne) {
        this.menu.close();
        this.nav.push(__WEBPACK_IMPORTED_MODULE_7__cmy_liste_historique_cmy_liste_historique__["a" /* ListeHistorique */], { theEvent: eventSelectionne });
    };
    ;
    ListeEvent.prototype.close = function (eventSelectionne) {
        var _this = this;
        if (eventSelectionne.roles.indexOf("Createur") < 0) {
            this.constante.presentToast("Seul le créateur peut annuler un event!");
            return;
        }
        ;
        var alert = this.alertController.create({
            title: "Fermer l'event?",
            message: "Confirmez-vous que vous voulez fermer cet event?",
            buttons: [
                {
                    text: 'Oui',
                    role: 'cancel',
                    handler: function () {
                        console.log("Fermeture de " + eventSelectionne.id);
                        var eventRest = _this.restangular.copy(eventSelectionne);
                        eventRest.route = "event";
                        eventRest.etat = "Clos";
                        eventRest.save().toPromise().then();
                        _this.loading = _this.loadingCtrl.create();
                        _this.loading.present();
                        eventRest.save().toPromise().then(function (resp) {
                            _this.loading.dismissAll();
                            eventSelectionne.etat = "Clos";
                            _this.menu.close();
                        }, function (errorResponse) {
                            _this.constante.traiteErreur(errorResponse, _this);
                        });
                    }
                },
                {
                    text: 'Non',
                    handler: function () {
                        console.log("Abandon");
                    }
                }
            ]
        });
        alert.present();
    };
    ;
    ListeEvent.prototype.showMenu = function (event) {
        this.visible = true;
        this.menu.show(this, event, event.urlPhoto);
        this.menu.toggle();
    };
    ;
    ListeEvent.prototype.closeMenu = function () {
        this.visible = false;
        this.menu.toggle();
        this.menu.close();
    };
    ;
    ListeEvent.prototype.copy = function (event) {
        console.log("Copy de EVENT : " + event.libelle);
    };
    ;
    ListeEvent.prototype.open = function (eventSelectionne) {
        console.log("Ouverture de " + eventSelectionne.id);
        this.nav.push(__WEBPACK_IMPORTED_MODULE_3__cmy_detail_event_cmy_detail_event__["a" /* DetailEventPage */], { theEvent: eventSelectionne });
    };
    ;
    ListeEvent.prototype.filtreEvent = function (val) {
        var _this = this;
        console.log('Filtre');
        this.valeurFiltre = val;
        // if the value is an empty string don't filter the items
        this.events = this.eventsComplet.filter(function (item) {
            var bool1 = true;
            var bool2 = true;
            if (val && val.trim() != '') {
                bool1 = JSON.stringify(item).toLocaleLowerCase().indexOf(val.toLowerCase()) > -1;
            }
            if (_this.filtreEtat != null && _this.filtreEtat.length > 0)
                bool2 = _this.filtreEtat.indexOf(item.etat) > -1;
            return bool1 && bool2;
        });
        this.calculResume(null);
    };
    ;
    ListeEvent.prototype.blockEvent = function () {
        console.log("Il faut bloquer!!!");
        //    this.parent.cover.nativeElement.style.display="none";
        this.visible = false;
    };
    ;
    return ListeEvent;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('menu'),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_6__components_menu_circular_menu_circular__["a" /* MenuCircular */])
], ListeEvent.prototype, "menu", void 0);
ListeEvent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'liste-event',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-event\cmy-liste-event.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Mes Events</ion-title>\n    <p style="color:white" no-padding no-margin>({{ depenseTotale.toFixed(2) }} € dus)</p>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="list-mini-content">\n  <div #cover id="cover" [ngClass]="{\'invisible\':!visible}" (tap)="blockEvent()" ion-fixed></div>\n  <menu-circular #menu class="circular-menu" [ngClass]="{\'invisible\':!visible}" ion-fixed></menu-circular>\n  <ion-toolbar >\n    <ion-searchbar (ionInput)="filtreEvent($event.target.value)">></ion-searchbar>\n    <ion-buttons right>\n      <button color="primary" ion-button icon-only (click)=\'filtreType()\'>\n        <ion-icon name="funnel"></ion-icon>\n      </button>\n    </ion-buttons>\n  </ion-toolbar>\n <div class="list-mini">\n    <ion-list>\n      <button class="list-item" (press)="showMenu(event)"  (tap)="open(event)" ion-item *ngFor="let event of events">\n        <ion-row align-items-center no-padding class="content-row one-line">\n          <!-- You can limit the rows of the description by using the class one-line. If you remove it, all the content from the row will be shown -->\n          <ion-col no-padding col-3 class="item-avatar">\n            <preload-image class="avatar-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(event.urlPhoto)\'></preload-image>\n          </ion-col>\n          <ion-badge class="etat" *ngIf="event.etat==\'En cours de solde\'" color="danger">\n            En Clôture\n          </ion-badge>\n          <ion-badge class="etat" *ngIf="event.etat==\'Annulé\'" color="primary">\n            Annulé\n          </ion-badge>\n          <ion-badge class="etat" *ngIf="event.etat==\'Clos\'" color="secondary">\n            Clos!\n          </ion-badge>\n          <ion-col no-padding col-8 class="item-content">\n            <h3 class="item-title"> {{event.libelle}}</h3>\n            <p class="item-description"> Date : {{event.date}}</p>\n            <p class="item-description"> Coût total : {{event.montantTotal.toFixed(2)}} €</p>\n            <p class="item-description"> Montant Dépensé : {{event.montantDepense.toFixed(2)}} €</p>\n          </ion-col>\n          <ion-col no-padding col-1 >\n            <ion-icon name="warning"  style="color : red;" *ngIf="event.montantDu>0"></ion-icon>\n          </ion-col>\n        </ion-row>\n      </button>\n    </ion-list>\n  </div>\n  <ion-row style="height: 150px"></ion-row>\n  <ion-fab right bottom>\n    <button (tap)="createNewEvent()" ion-fab color="danger"><ion-icon name="add"></ion-icon></button>\n  </ion-fab>\n</ion-content>\n\n\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-event\cmy-liste-event.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */], __WEBPACK_IMPORTED_MODULE_5_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["a" /* AlertController */]])
], ListeEvent);

//# sourceMappingURL=cmy-liste-event.js.map

/***/ }),

/***/ 8:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Constante; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "l", function() { return User; });
/* unused harmony export UserAvecDepense */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "f", function() { return Invitation; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "e", function() { return Event; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "g", function() { return LienEventUser; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "i", function() { return Mouvement; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return Depense; });
/* unused harmony export TypeOperation */
/* unused harmony export Operation */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "j", function() { return OperationAvecDepense; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return Contact; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "h", function() { return Message; });
/* unused harmony export TableauOperation */
/* unused harmony export TableauMessage */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "k", function() { return Ordre; });
/* unused harmony export Historique */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "d", function() { return Document; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject__ = __webpack_require__(175);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var Constante = (function () {
    function Constante(toastCtrl) {
        this.toastCtrl = toastCtrl;
        this.BASE_URL_REST = 'http://vulcanjibe.ddns.net:8080/CoMoneyTy-0.0.1-SNAPSHOT/rest';
        this.BASE_URL_IMAGE = 'http://vulcanjibe.ddns.net:8080/Image';
        this.userChange = new __WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject__["BehaviorSubject"](this.user);
        this.eventChange = new __WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject__["BehaviorSubject"](this.event);
    }
    Constante.prototype.touchEvent = function (newEvent) {
        this.event = newEvent;
        this.eventChange.next(newEvent);
    };
    Constante.prototype.login = function (newUser) {
        this.user = newUser;
        this.userChange.next(newUser);
    };
    Constante.prototype.logout = function () {
        //this.user = newUser;
        this.user.nom = "....";
        this.userChange.next(this.user);
    };
    Constante.prototype.getUrlImage = function (url) {
        if (url.startsWith("http"))
            return url;
        if (url.startsWith("content:"))
            return url;
        return this.BASE_URL_IMAGE + "/" + url;
    };
    Constante.prototype.presentToast = function (text) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    Constante.prototype.presentToastAvecPosition = function (text, position) {
        var toast = this.toastCtrl.create({
            message: text,
            duration: 3000,
            position: position
        });
        toast.present();
    };
    Constante.prototype.traiteErreur = function (error, component) {
        console.log("ERREUR=>", error);
        if (component.loading != null)
            component.loading.dismissAll();
        var msg = "Une erreur technique est survenue!!!";
        if (error.data && error.data.message) {
            msg = error.data.message;
        }
        else {
            msg += " : " + error;
        }
        var toast = this.toastCtrl.create({
            message: msg,
            duration: 3000,
            position: 'top'
        });
        toast.present();
    };
    return Constante;
}());
Constante = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0_ionic_angular__["n" /* ToastController */]])
], Constante);

var User = (function () {
    function User() {
    }
    return User;
}());

var UserAvecDepense = (function () {
    function UserAvecDepense(user1) {
        this.user = user1;
        this.aPaye = 0;
        this.doit = 0;
    }
    return UserAvecDepense;
}());

var Invitation = (function () {
    function Invitation() {
    }
    return Invitation;
}());

var Event = (function () {
    function Event() {
    }
    return Event;
}());

var LienEventUser = (function () {
    function LienEventUser(id1, id2) {
        this.userId = id1;
        this.eventId = id2;
    }
    return LienEventUser;
}());

var Mouvement = (function () {
    function Mouvement(idEmet, idEv) {
        this.idEmetteur = idEmet;
        this.idEvent = idEv;
    }
    return Mouvement;
}());

var Depense = (function () {
    function Depense(idPay, idEv) {
        this.idPayeur = idPay;
        this.idEvent = idEv;
    }
    return Depense;
}());

var TypeOperation = (function () {
    function TypeOperation() {
    }
    return TypeOperation;
}());

var Operation = (function () {
    function Operation() {
    }
    return Operation;
}());

var OperationAvecDepense = (function () {
    function OperationAvecDepense() {
    }
    return OperationAvecDepense;
}());

var Contact = (function () {
    function Contact(nom, prenom, phone) {
        this.idInterne = nom + "." + prenom;
        this.email = nom + "." + prenom + "@gmail.com";
        this.phoneNumber = "06 82 66 79 21";
        this.displayName = nom + " " + prenom;
        this.photo = "user/inconnu.png";
    }
    return Contact;
}());

var Message = (function () {
    function Message() {
    }
    return Message;
}());

var TableauOperation = (function () {
    function TableauOperation() {
    }
    return TableauOperation;
}());

var TableauMessage = (function () {
    function TableauMessage() {
    }
    return TableauMessage;
}());

var Ordre = (function () {
    function Ordre() {
    }
    return Ordre;
}());

var Historique = (function () {
    function Historique() {
    }
    return Historique;
}());

var Document = (function () {
    function Document() {
    }
    return Document;
}());

//# sourceMappingURL=cmy.model.js.map

/***/ }),

/***/ 82:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Home; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_gestion_ami_cmy_gestion_ami__ = __webpack_require__(215);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_liste_event_cmy_liste_event__ = __webpack_require__(71);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__cmy_list_message_cmy_liste_message__ = __webpack_require__(217);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__cmy_gestion_profile_cmy_gestion_profile__ = __webpack_require__(125);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








//import 'rxjs/Rx';
var Home = (function () {
    function Home(nav, constante, loadingCtrl, restangular, alertController) {
        this.nav = nav;
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.restangular = restangular;
        this.alertController = alertController;
        this.montantQueJeDois = 0;
        this.nbEvent = 0;
        this.montantQuonMeDoit = 0;
        this.nbMessageATraiter = 0;
        this.nbAmis = 0;
        this.profil = "--";
    }
    ;
    Home.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.restangular.one("user/" + this.constante.user.id + "/synthese").get().toPromise().then(function (rep) {
            _this.montantQueJeDois = rep.montantQueJeDois;
            _this.nbEvent = rep.nbEvent;
            _this.montantQuonMeDoit = rep.montantQuonMeDoit;
            _this.nbMessageATraiter = rep.nbMessageATraiter;
            _this.nbAmis = rep.nbAmis;
            _this.profil = (rep.profil > 0 ? "complet" : "incomplet");
            _this.profilColor = (rep.profil > 0 ? "secondary" : "danger");
            if (_this.constante.user == null && _this.constante.user == null) {
                var alert_1 = _this.alertController.create({
                    title: 'Votre profil est incomplet (Téléphone!)',
                    message: "Voulez-vous accéder directement à votre profil pour le compléter?",
                    buttons: [
                        {
                            text: 'Oui',
                            role: 'cancel',
                            handler: function () {
                                _this.nav.push(__WEBPACK_IMPORTED_MODULE_7__cmy_gestion_profile_cmy_gestion_profile__["a" /* GestionProfile */]);
                            }
                        },
                        {
                            text: 'Non',
                            handler: function () {
                            }
                        }
                    ]
                });
                alert_1.present();
            }
        }, function (error) {
            _this.constante.traiteErreur(error, _this);
        });
    };
    ;
    Home.prototype.goToAmis = function () {
        this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_4__cmy_gestion_ami_cmy_gestion_ami__["a" /* GestionAmi */]);
    };
    ;
    Home.prototype.goToProfile = function () {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_7__cmy_gestion_profile_cmy_gestion_profile__["a" /* GestionProfile */]);
    };
    ;
    Home.prototype.goToEvent = function () {
        this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_5__cmy_liste_event_cmy_liste_event__["a" /* ListeEvent */]);
    };
    ;
    Home.prototype.goToMessage = function () {
        this.nav.setRoot(__WEBPACK_IMPORTED_MODULE_6__cmy_list_message_cmy_liste_message__["a" /* ListeMessage */]);
    };
    ;
    return Home;
}());
Home = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'home',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-home\cmy-home.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Mon Tableau de bord</ion-title>\n  </ion-navbar>\n</ion-header>\n\n\n<ion-content class="home">\n\n\n  <section>\n    <ion-item >\n      <div text-wrap (tap)="goToProfile()" class="drop-shadow">\n        <p>Mon Profil :</p><br>\n        <ion-badge  item-end [color]="profilColor">{{profil}}</ion-badge>\n      </div>\n      <div text-wrap (tap)="goToEvent()" class="drop-shadow">\n        <p>Event en cours : </p>\n        <ion-badge  item-end>{{nbEvent}}</ion-badge>\n      </div>\n    </ion-item>\n    <ion-item >\n      <div text-wrap class="drop-shadow">\n        <p>Je dois à mes amis : </p>\n        <ion-badge color="danger" item-end>{{montantQueJeDois}} €</ion-badge>\n      </div>\n      <div text-wrap class="drop-shadow">\n        <p>Mes amis me doivent : </p>\n        <ion-badge color="danger" item-end>{{montantQuonMeDoit}} €</ion-badge>\n      </div>\n    </ion-item>\n\n    <ion-item >\n      <div text-wrap (tap)="goToMessage()" class="drop-shadow">\n        <p>Messages à traiter : </p>\n        <ion-badge item-end>{{nbMessageATraiter}}</ion-badge>\n      </div>\n      <div text-wrap (tap)="goToAmis()"  class="drop-shadow">\n        <p>Mes amis : </p><br>\n        <ion-badge color="danger" item-end>{{nbAmis}}</ion-badge>\n      </div>\n    </ion-item>\n\n  </section>\n\n\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-home\cmy-home.html"*/,
        providers: [__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"]]
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_3__cmy_model_cmy_model__["a" /* Constante */], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_2_ionic_angular__["a" /* AlertController */]])
], Home);

//# sourceMappingURL=cmy-home.js.map

/***/ }),

/***/ 911:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EventService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var EventService = (function () {
    function EventService(restangular) {
        this.restangular = restangular;
    }
    EventService.prototype.getEvents = function (user) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.restangular.all('user/' + user.id + '/events').getList().subscribe(function (events) {
                resolve(events);
            }, function (errorResponse) {
                reject(errorResponse);
            });
        });
    };
    return EventService;
}());
EventService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1_ngx_restangular__["Restangular"]])
], EventService);

//# sourceMappingURL=event.service.js.map

/***/ }),

/***/ 912:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PreloadImage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular_util_util__ = __webpack_require__(2);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var PreloadImage = (function () {
    function PreloadImage(_elementRef, _renderer) {
        this._elementRef = _elementRef;
        this._renderer = _renderer;
        this._src = '';
        this._img = new Image();
    }
    Object.defineProperty(PreloadImage.prototype, "src", {
        set: function (val) {
            this._src = Object(__WEBPACK_IMPORTED_MODULE_1_ionic_angular_util_util__["l" /* isPresent */])(val) ? val : '';
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(PreloadImage.prototype, "ratio", {
        set: function (ratio) {
            this._ratio = ratio || null;
        },
        enumerable: true,
        configurable: true
    });
    PreloadImage.prototype.ngOnChanges = function (changes) {
        var ratio_height = (this._ratio.h / this._ratio.w * 100) + "%";
        // Conserve aspect ratio (see: http://stackoverflow.com/a/10441480/1116959)
        this._renderer.setElementStyle(this._elementRef.nativeElement, 'padding-bottom', ratio_height);
        this._update();
        // console.log("CHANGES preload-image", this._src);
        // console.log(changes['src'].isFirstChange());
    };
    PreloadImage.prototype._update = function () {
        var _this = this;
        if (Object(__WEBPACK_IMPORTED_MODULE_1_ionic_angular_util_util__["l" /* isPresent */])(this.alt)) {
            this._img.alt = this.alt;
        }
        if (Object(__WEBPACK_IMPORTED_MODULE_1_ionic_angular_util_util__["l" /* isPresent */])(this.title)) {
            this._img.title = this.title;
        }
        this._img.addEventListener('load', function () {
            _this._elementRef.nativeElement.appendChild(_this._img);
            _this._loaded(true);
        });
        this._img.src = this._src;
        this._loaded(false);
    };
    PreloadImage.prototype._loaded = function (isLoaded) {
        this._elementRef.nativeElement.classList[isLoaded ? 'add' : 'remove']('img-loaded');
    };
    return PreloadImage;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", String)
], PreloadImage.prototype, "alt", void 0);
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", String)
], PreloadImage.prototype, "title", void 0);
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", String),
    __metadata("design:paramtypes", [String])
], PreloadImage.prototype, "src", null);
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Object),
    __metadata("design:paramtypes", [Object])
], PreloadImage.prototype, "ratio", null);
PreloadImage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'preload-image',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\components\preload-image\preload-image.html"*/'<ion-spinner name="bubbles"></ion-spinner>\n<ng-content></ng-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\components\preload-image\preload-image.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"], __WEBPACK_IMPORTED_MODULE_0__angular_core__["Renderer"]])
], PreloadImage);

//# sourceMappingURL=preload-image.js.map

/***/ }),

/***/ 913:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BackgroundImage; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular_util_util__ = __webpack_require__(2);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var BackgroundImage = (function () {
    function BackgroundImage(_elementRef, _renderer) {
        this._elementRef = _elementRef;
        this._renderer = _renderer;
        this._src = '';
    }
    Object.defineProperty(BackgroundImage.prototype, "src", {
        set: function (val) {
            this._src = Object(__WEBPACK_IMPORTED_MODULE_1_ionic_angular_util_util__["l" /* isPresent */])(val) ? val : '';
        },
        enumerable: true,
        configurable: true
    });
    BackgroundImage.prototype.ngOnChanges = function (changes) {
        this._update();
        // console.log("CHANGES background-image", this._src);
        // console.log(changes['src'].isFirstChange());
    };
    BackgroundImage.prototype._update = function () {
        var _this = this;
        var img = new Image();
        img.addEventListener('load', function () {
            _this._elementRef.nativeElement.style.backgroundImage = 'url(' + _this._src + ')';
            _this._loaded(true);
        });
        img.src = this._src;
        this._loaded(false);
    };
    BackgroundImage.prototype._loaded = function (isLoaded) {
        this._elementRef.nativeElement.classList[isLoaded ? 'add' : 'remove']('img-loaded');
    };
    return BackgroundImage;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", String)
], BackgroundImage.prototype, "class", void 0);
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", String),
    __metadata("design:paramtypes", [String])
], BackgroundImage.prototype, "src", null);
BackgroundImage = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'background-image',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\components\background-image\background-image.html"*/'<span class="bg-overlay"></span>\n<ion-spinner name="bubbles"></ion-spinner>\n<ng-content></ng-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\components\background-image\background-image.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"], __WEBPACK_IMPORTED_MODULE_0__angular_core__["Renderer"]])
], BackgroundImage);

//# sourceMappingURL=background-image.js.map

/***/ }),

/***/ 914:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ShowHideContainer; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__show_hide_input__ = __webpack_require__(424);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var ShowHideContainer = (function () {
    function ShowHideContainer() {
        this.show = false;
    }
    ShowHideContainer.prototype.toggleShow = function () {
        this.show = !this.show;
        if (this.show) {
            this.input.changeType("text");
        }
        else {
            this.input.changeType("password");
        }
    };
    return ShowHideContainer;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ContentChild"])(__WEBPACK_IMPORTED_MODULE_1__show_hide_input__["a" /* ShowHideInput */]),
    __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1__show_hide_input__["a" /* ShowHideInput */])
], ShowHideContainer.prototype, "input", void 0);
ShowHideContainer = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'show-hide-container',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\components\show-hide-password\show-hide-password.html"*/'<ng-content></ng-content>\n<a class="type-toggle" (tap)="toggleShow()">\n	<ion-icon class="show-option" [hidden]="show" name="eye"></ion-icon>\n	<ion-icon class="hide-option" [hidden]="!show" name="eye-off"></ion-icon>\n</a>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\components\show-hide-password\show-hide-password.html"*/,
        host: {
            'class': 'show-hide-password'
        }
    }),
    __metadata("design:paramtypes", [])
], ShowHideContainer);

//# sourceMappingURL=show-hide-container.js.map

/***/ }),

/***/ 915:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ColorRadio; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var ColorRadio = (function () {
    function ColorRadio(el, renderer) {
        this.el = el;
        this.renderer = renderer;
    }
    ColorRadio.prototype.setColor = function (color) {
        this.renderer.setElementStyle(this.el.nativeElement, 'backgroundColor', color);
        this.renderer.setElementStyle(this.el.nativeElement, 'borderColor', color);
    };
    ColorRadio.prototype.ngOnInit = function () {
        console.log(this.color);
        this.setColor(this.color);
    };
    return ColorRadio;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])('color-radio'),
    __metadata("design:type", String)
], ColorRadio.prototype, "color", void 0);
ColorRadio = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Directive"])({
        selector: '[color-radio]'
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"], __WEBPACK_IMPORTED_MODULE_0__angular_core__["Renderer"]])
], ColorRadio);

//# sourceMappingURL=color-radio.js.map

/***/ }),

/***/ 918:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ListeOrdre; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ionic_angular__ = __webpack_require__(5);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__ = __webpack_require__(8);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__ = __webpack_require__(10);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_ngx_restangular___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_ngx_restangular__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__cmy_detail_ordre_cmy_detail_ordre__ = __webpack_require__(219);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__cmy_paiement_ordre_cmy_paiement_ordre__ = __webpack_require__(218);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






//import 'rxjs/Rx';
var ListeOrdre = (function () {
    function ListeOrdre(constante, loadingCtrl, nav, restangular, toastCtrl) {
        this.constante = constante;
        this.loadingCtrl = loadingCtrl;
        this.nav = nav;
        this.restangular = restangular;
        this.toastCtrl = toastCtrl;
        this.loading = this.loadingCtrl.create();
    }
    ListeOrdre.prototype.ionViewDidLoad = function () {
        var _this = this;
        this.loading.present();
        // This will query /accounts and return a observable.
        this.restangular.all('user/ordres').getList().subscribe(function (ordres) {
            _this.ordres = ordres;
            _this.loading.dismiss();
        }, function (errorResponse) {
            _this.constante.traiteErreur(errorResponse, _this);
        });
    };
    ;
    ListeOrdre.prototype.detail = function (ordre) {
        this.nav.push(__WEBPACK_IMPORTED_MODULE_4__cmy_detail_ordre_cmy_detail_ordre__["a" /* DetailOrdre */], { theOrdre: ordre });
    };
    ;
    ListeOrdre.prototype.traite = function (ordre) {
        if (ordre.mouvement.etat == "Réalisé") {
            var toast = this.toastCtrl.create({
                message: "Le paiement a déjà été réalisé!",
                duration: 3000,
                position: 'top'
            });
            toast.present();
            return;
        }
        this.nav.push(__WEBPACK_IMPORTED_MODULE_5__cmy_paiement_ordre_cmy_paiement_ordre__["a" /* PaiementOrdre */], { theOrdre: ordre });
    };
    ;
    return ListeOrdre;
}());
ListeOrdre = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'liste-ordre',template:/*ion-inline-start:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-ordre\cmy-liste-ordre.html"*/'<ion-header>\n  <ion-navbar>\n    <button ion-button menuToggle>\n      <ion-icon name="menu"></ion-icon>\n    </button>\n    <ion-title>Ordre à traiter</ion-title>\n  </ion-navbar>\n</ion-header>\n\n<ion-content class="notifications-content">\n  <ion-item-group>\n    <ion-item-divider class="notifications-divider">Today</ion-item-divider>\n    <ion-item [ngClass]="{\'row-opaque\' : ordre.mouvement.etat!=\'Transmis\'}" (tap)="detail(ordre);" (press)="traite(ordre);" class="notification-item" *ngFor="let ordre of ordres">\n      <ion-avatar item-left>\n        <preload-image class="user-image" [ratio]="{w:1, h:1}" [src]=\'constante.getUrlImage(ordre.emetteur.urlAvatar)\'></preload-image>\n      </ion-avatar>\n      <h2 class="item-title" text-wrap>Règlement pour {{ordre.event.libelle}} du {{ordre.event.date}}</h2>\n      <p class="item-description">{{ordre.mouvement.montant}}€ à payer à {{ordre.emetteur.nom}} {{ordre.emetteur.prenom}}</p>\n      <ion-note class="item-time" item-right>{{ordre.mouvement.date}}</ion-note>\n    </ion-item>\n  </ion-item-group>\n</ion-content>\n'/*ion-inline-end:"F:\Nodejs\CoMoneyTy\src\pages\cmy-liste-ordre\cmy-liste-ordre.html"*/
    }),
    __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__cmy_model_cmy_model__["a" /* Constante */],
        __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["f" /* LoadingController */], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["j" /* NavController */], __WEBPACK_IMPORTED_MODULE_3_ngx_restangular__["Restangular"], __WEBPACK_IMPORTED_MODULE_1_ionic_angular__["n" /* ToastController */]])
], ListeOrdre);

//# sourceMappingURL=cmy-liste-ordre.js.map

/***/ })

},[425]);
//# sourceMappingURL=main.js.map