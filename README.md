# MyGrant
> An Android application to manage student grants

![](https://img.shields.io/badge/license-MIT-blue)
![](https://img.shields.io/badge/version-1.0.0-orange)
![](https://img.shields.io/badge/itextpdf-7.1.9-blue)
![](https://img.shields.io/badge/Apache_Velocity-2.2-purple)
![](https://img.shields.io/badge/PostgreSQL-13.1-green)
![](https://img.shields.io/badge/materialfilepicker-1.9.1-red)
![](https://img.shields.io/badge/circleimageview-3.1.0-yellow)
![](https://img.shields.io/badge/maildroid-0.0.6-brown)


## Prerequisites

- Install Android Studio :
> From : [Android Studio](https://developer.android.com/studio)

- PostgreSQL 13 :
> Using [Docker](https://www.docker.com) :
```shell
docker pull postgres:13
docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
docker ps
```

- Create the database using the queries from the file : database.sql :


- Edit settings.xml file :

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="database_server_ip">192.168.1.4</string>
    <string name="database_server_port">5432</string>
    <string name="database_name">mygrant</string>
    <string name="database_user">postgres</string>
    <string name="database_password">postgres</string>
    <string name="email">my.grant.ensetm@gmail.com</string>
    <string name="password"></string>
    <string name="nexmo_api_key"></string>
    <string name="nexmo_api_secret"></string>
</resources>
```

## Screenshots
| Splash screen | Log in | Register |
| :---: | :---: | :---: |
| ![](screenshots/1.png) | ![](screenshots/2.png) | ![](screenshots/3.png) |

| Reset password | Student dashboard | Submit a complaint |
| :---: | :---: | :---: |
| ![](screenshots/4.png) | ![](screenshots/5.png) | ![](screenshots/6.png) |

| View grants list | Filling information | Admin dashboard |
| :---: | :---: | :---: |
| ![](screenshots/7.png) | ![](screenshots/8.png) | ![](screenshots/9.png) |

| Add an university | Add an establishment | Add an establishment |
| :---: | :---: | :---: |
| ![](screenshots/10.png) | ![](screenshots/11.png) | ![](screenshots/12.png) |


## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
[MIT License](https://choosealicense.com/licenses/mit/)
