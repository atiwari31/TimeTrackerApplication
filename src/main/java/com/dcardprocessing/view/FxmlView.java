package com.dcardprocessing.view;

import java.util.ResourceBundle;

public enum FxmlView {

	USER {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("user.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/User.fxml";
		}
	},
	USER_HOME {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("user.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/UserHome.fxml";
		}
	},
	USER_FILE_SCAN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("user.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/UserFileScan.fxml";
		}
	},
	ADMIN_REVIEW_FILE_SCAN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("admin.review.file.scan.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/AdminReviewFileScan.fxml";
		}
	},
	ADMIN_REVIEW_FILE_SCAN_IP {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("admin.review.file.scan.ip.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/AdminReviewIPScan.fxml";
		}
	},
	ADMIN_LOG {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("admin.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/AdminLog.fxml";
		}
	},
	ADMIN_LOG_DETAIL {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("admin.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Dashboard.fxml";
		}
	},

	ADMIN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("admin.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Admin.fxml";
		}
	},
	LOGIN {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("login.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/Login.fxml";
		}
	},
	MYSQL {
		@Override
		public String getTitle() {
			return getStringFromResourceBundle("MYSQL.title");
		}

		@Override
		public String getFxmlFile() {
			return "/fxml/MySql.fxml";
		}
	};

 

	public abstract String getTitle();

	public abstract String getFxmlFile();

	String getStringFromResourceBundle(String key) {
		return ResourceBundle.getBundle("Bundle").getString(key);
	}

}
