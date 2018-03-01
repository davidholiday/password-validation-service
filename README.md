# extensible password validation service 

## key features

1. validates a given password and reports not only whether or not a given password passes validation but also a manifest of what validations were performed and the results of each.

2. currently checks for the following:

    * Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.

    * Must be between 5 and 12 characters in length.

    * Must not contain any sequence of characters immediately followed by the same sequence.

3. can be configured to check for a different set of things by dependency-injecting a different validator bundle into the endpoint controller 

4. new validation bundle components (ie - new validations) can be created by creating extending the PasswordValidatorComponentDecorator abstract class

5. can be run as a stand alone spring-boot application or compiled into a war file 