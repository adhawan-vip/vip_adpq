# How our Prototype meets CDT's Requirements
Based on CDT's requirements, we developed the following capabilities in our prototype:

## Knowledge Creation
### Requirement
* Knowledge Creation:  The Working Prototype should:
  * Have the ability to easily create “knowledge articles” (KAs)
     * These can be original records (e.g., specific work instructions or content) and/or packages of content, including documents, user-configurable forms, tables, and workflows
  * Have the ability to provide multiple levels and formats of information in KAs (e.g., bullet points for senior technical levels, scripted specific details for junior/non-technical staff)
  * Allow for role-based security access, to allow control of access and level of information by login

### How We Met the Requirement
Our prototype allows the creation of Articles which can be used to track various types of knowledge such as Job Aids, Content, and Packages.  We allow upload of documents and each uploaded document can be associated to an article.  Articles can act as packages of content by virtue of being associated with various documents.  

Our prototype allows for creation of content using a WYSIWYG (What You See Is What You Get) editor which fully supports wikipedia style formatting including bullet points and hyperlinks.  Additionally, our prototype will automatically convert any authored content entered into the WYSIWYG editor into an auto-generated PDF document and associate it to the article, allowing for easy sharing.

## Knowledge Sharing
### Requirement
* Knowledge Sharing: The Working Prototype should:
  * Allow for the promotion of process and information across systems and channels, as required
  * Have the ability to create user-defined rules for creation (e.g., mandatory fields) and lifecycle management (e.g., who, how, when revised and updated)
  * Trigger escalation processes (e.g., automated emails/texts to approvers, reminders) for lifecycle activities

### How We Met the Requirement
Our prototype allows for promotion of information as required.  We accomplish this using a simple workflow where by Authors can create articles, which are saved as Drafts.  Once an article is created in the Draft status, the system automatically creates a Task for a Reviewer to review the article.  Once the Reviewer closes the task, the article is automatically published.  Users can view Published articles only.

We implement mandatory fields such as an article's name and published date etc.  For each created article, we maintain an audit trail including who authored, when created, who modified, and when modified.

Each authored article automatically gets converted into a PDF to allow for easy sharing.

## Knowledge Development
### Requirement
* Knowledge Development: The Working Prototype should:
  * Have the ability to update and improve KAs and access the value of usage as input to predicting new records or record types
  * Show innovation by learning from existing records (e.g., types, content, usage) and prompting to create new KAs

### How We Met the Requirement
Our prototype allows reviewers to review created articles and authors to update existing articles.  Users can rate articles to provide feedback to authors which can then be used as a gauge of stakeholder interest in related topics.  This information can be used to identify additional topics of interest.