# Klmfullstack
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
</head>

<body>
    <div class="container">
        <div class="row">
            <h3><b><u>Flight Travel Price estimation </u></b></h3>
            <p><b>Description : </b><br/>
                -> FullStack application that enable user to filter the Airports on Ui 
                and get offered price from resource server using oauth underhood.</p><br/>
              -> UI has developed with using Angular 8 and Backend Rest api's are developed using
                springBoot.
            </p>
            <p>-> Spring Boot RestApi Use Restapis provided by MockService to get data for Airports and price between them.</p>
            <P>-> A statistics Page was also developed with in the angular that views Statistics requestes.
            </p>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <h3> => Steps to load and Run the programmes</h3>
            <h3>RestApi's :KlmOauthClient </h3>
            <h4>Prerequists</h4>
            <p>-> System should be installed with JDK 8 or higher,Maven,Ecplice or Spring tool Suite.</p>
            <h4>Load code to workspace</h4>
            <p>-> Considering with above configuration,Import project as existing ,Load the project to Spring Tools Suite or Ecplise (Configured with Spring tools).</p>
            <p> -> <b><i>import >> Maven Existing Project >> browse to navigate the project folder >>select pom file >> next until finish.</i></b> </p>
            <p> ->Give some to time to setup dependencies as per pom.xml</p>
            <p>->Once the project loaded all its dependencies to the class path and displayed code structue.
            <p></p>
            <h4>Launch StringBoot RestApis'</h4>
            <p>we wil use ecplise to run the applciation.</p>
            <p>select project in the workspace and select file and opt for </p>
            <b><i>Project folder >> Run as >> Spring boot application</i></b>. Thats all application will be hosted on port 8002;</p>
            <h3>Angular Ui</h3>
            <h4>Prerequists</h4>
            <p>System should be installed with node js and angular cli</p>
            <h4>Hosting the project locally</h4>
            <p>open cmd in cloned angular directory and run below commands</p>
            <p><b>npm init</b></p>
            <p><b>ng serve --open</b></p>
            <p>by the above command compiler will build the project and will open a new tab <a href="http://localhost:4200/">http://localhost:4200/</a> in browser. </p>
            <p><b>statistics :</b> can access by seecting statistics on the navigation bar</p>
        </div>
    </div>
</body>
</html>
