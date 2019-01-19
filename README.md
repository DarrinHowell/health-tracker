# Health Tracker

### Author: 
Darrin Howell

### Overview: 
The vision behind building this app is to provide a lightweight resource for app users to 
initiate positive, healthy change in their lives. <br/> 

The Health Tracker app enables user to track exercise progress, use a built in stopwatch, 
receive recurring reminders for hydration, and perform finger flexor exercises. <br/>

To open and use this app, download this repo and install on either a physical android
device or on an android emulator. <br/>

# Change Log

## 20190108:
* Initial setup of app.
* Added counter feature upon button click.
* Added buttons that control a stop watch.
* Stopwatch keeping track of time elapsed. 

## 20190109:
* Separated activities into different views. 
* Notifications being scheduled in the future. 

## 20190110:
* Added annotations to Room database implementation
* Having compiler errors adding to the database

## 20190116
* Added form to exercise diary
* Database rendering data
* Database updating when input entered into form

## 20190117
* Set up Springboot API which allows app users to make get and post requests to and from the 
server database
* Deployed API on heorku at https://health-tracker-backend-dbh.herokuapp.com/

## 20190118
* Rendering data from server database and local database on page load. 
 * Only pulling data from server onto local device if it is not present on the local database. 


## Features on display: 

#### Activities partitioned into separate views

![home view](./app/changelog_assets/healthTracker_ActivitiesBrokenUp.png)

#### Finger Flexor, no clicks

![home view](./app/changelog_assets/health-tracker_fingerFlexor_noClicks.png)

#### Finger Flexor, 10 clicks

![home view](./app/changelog_assets/heatlh-tracker_fingerFlexor_tenClicks.png)

#### Stop watch, not started

![home view](./app/changelog_assets/heatlh-tracker_stopWatch_NotStarted.png)

#### Stop watch, time elapsed

![home view](./app/changelog_assets/health-tracker_stopWatch_timeElapsed.png)

#### Stop watch, paused

![home view](./app/changelog_assets/health-tracker_stopWatch_paused.png)

#### Stop watch, reset

![home view](./app/changelog_assets/health-tracker_stopwatch_reset.png)

#### Notification sent

![home view](./app/changelog_assets/health-tracker_notificationSent_onSchedule.png)

#### Exercise Diary with form

![home view](./app/changelog_assets/health-tracker_exerciseDiaryAndForm.png)