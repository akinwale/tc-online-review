<%@ page language="java" isELIgnored="false" %>
<%@ taglib prefix="html" uri="/tags/struts-html" %>
<%@ taglib prefix="bean" uri="/tags/struts-bean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html xhtml="true">

<head>
	<title><bean:message key="OnlineReviewApp.title" /></title>

	<!-- TopCoder CSS -->
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/coders.css">
	<link type="text/css" rel="stylesheet" href="../css/stats.css">
	<link type="text/css" rel="stylesheet" href="../css/tcStyles.css">

	<!-- JS from wireframes -->
	<script language="javascript" type="text/javascript" src="../scripts/popup.js"></script>
	<script language="javascript" type="text/javascript" src="../scripts/expand_collapse.js"></script>

	<!-- CSS and JS by Petar -->
	<link type="text/css" rel="stylesheet" href="../css/new_styles.css">
	<script language="JavaScript" type="text/javascript" src="../scripts/rollovers.js"></script>
</head>

<body>
	<jsp:include page="../includes/inc_header.jsp" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<!-- Left Column Begins-->
			<td width="180"><jsp:include page="../includes/inc_leftnav.jsp" /></td>
			<!-- Left Column Ends -->

			<!-- Gutter Begins -->
			<td width="15"><img src="../i/clear.gif" width="15" height="1" border="0"/></td>
			<!-- Gutter Ends -->

			<!-- Center Column Begins -->
			<td class="bodyText">
				<jsp:include page="../includes/project/project_tabs.jsp" />

				<div id="mainMiddleContent">
					<div style="margin: 11px 6px 9px 0px; ">
						<span class="bodyTitle"><bean:message key="userError.Attention" /></span><br />
						<b><bean:message key="userError.ErrorPrefix" />
						<span style="color: red; font-weight: bold;">${errorTitle}</span></b>
					</div>
					<div style="margin-top: 12px; margin-bottom: 32px;">
						<b><bean:message key="userError.Reason" /></b>
						${errorMessage}
					</div>
				</div>
			</td>
			<!-- Center Column Ends -->

			<!-- Gutter -->
			<td width="15"><img src="../i/clear.gif" width="25" height="1" border="0"></td>
			<!-- Gutter Ends -->

			<!-- Gutter -->
			<td width="2"><img src="../i/clear.gif" width="2" height="1" border="0"></td>
			<!-- Gutter Ends -->
		</tr>
	</table>

	<jsp:include page="../includes/inc_footer.jsp" />
</body>
</html:html>