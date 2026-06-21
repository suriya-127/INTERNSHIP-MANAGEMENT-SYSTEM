# Internship Management System

## Overview

The Internship Management System helps educational institutions, companies, and students manage the full lifecycle of internships — from posting opportunities to applying, reviewing, and onboarding interns. It provides role-based interfaces: students can browse and apply to internships; companies can create and manage postings and review applicants; administrators can manage users, postings, and system settings.

Core capabilities:

- Manage internship postings (create, edit, publish, archive)
- Application workflow (submit, review, shortlist, accept/decline)
- User management and role-based access
- Notifications and basic reporting
- Pluggable persistence layer (in-memory by default, optional DB)

Architecture overview:

- Models: `User`, `Company`, `Posting`, `Application`
- Services: `PostingService`, `ApplicationService`, `UserService`, `AuthService`
- UI/CLI: Simple console UI (extendable to a web frontend)
- Persistence: File/DB-backed via a DAO layer

Common workflows:

- A student searches postings and submits an application.
- A company posts an internship and reviews incoming applications.
- An admin moderates content, manages roles, and views basic reports.

This README contains quick start instructions below. For development, consider adding a module-level README per package and configuring a build tool (Maven/Gradle) for dependency management.

## Features

- Create, update, and list internship postings
- Student application submission and tracking
- Company dashboard to review applicants
- Admin tools for user and posting management
- Role-based access control

## Tech Stack

- Java (core application)
- (Optional) Any relational database (MySQL, PostgreSQL, SQLite)
- Build with `javac` / run with `java`

## Prerequisites

- Java JDK 11 or newer
- Optional: a database and JDBC driver if you enable persistence
