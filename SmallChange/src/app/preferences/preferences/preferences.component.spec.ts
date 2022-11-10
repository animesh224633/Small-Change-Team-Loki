import { Location } from "@angular/common";
import { TestBed, fakeAsync, tick } from "@angular/core/testing";
import { RouterTestingModule } from "@angular/router/testing";
import { Router } from "@angular/router";
import { PreferencesComponent } from "./preferences.component";
import { AppRoutingModule } from "src/app/app-routing.module";



describe("Router: App", () => {
  let location: Location;
  let router: Router;
  let fixture;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, AppRoutingModule,],
      declarations: [PreferencesComponent]
    });

    router = TestBed.get(Router);
    location = TestBed.get(Location);

    fixture = TestBed.createComponent(PreferencesComponent);
    router.initialNavigation();
  });

  it("fakeAsync works", fakeAsync(() => {
    let promise = new Promise(resolve => {
      setTimeout(resolve, 10);
    });
    let done = false;
    promise.then(() => (done = true));
    tick(50);
    expect(done).toBeTruthy();
  }));
  
  it('navigate to "search" takes you to /search', fakeAsync(() => {
    router.navigate(["preferencesPage"]).then(() => {
      expect(location.path()).toBe("/preferencesPage");
    });
  }));
});
